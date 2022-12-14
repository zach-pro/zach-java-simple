package com.zach.modular.authentication.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zach.common.constant.ResultCode;
import com.zach.common.utils.sys.UserUtils;
import com.zach.common.vo.JsonResult;
import com.zach.common.vo.ResultTool;
import com.zach.modular.authentication.user.entity.AiUser;
import com.zach.modular.authentication.user.enums.EnabledEnum;
import com.zach.modular.authentication.user.enums.LockedEnum;
import com.zach.modular.authentication.user.service.impl.AiUserServiceImpl;
import com.zach.modular.authentication.user_role_relation.entity.AiUserRoleRelation;
import com.zach.modular.authentication.user_role_relation.service.impl.AiUserRoleRelationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author apple
 * 
 */
@Tag(name = "用户模块", description = "用户模块")
@RestController
@RequestMapping("/ai_user/aiUser")
@Transactional(rollbackFor=Exception.class)
public class AiUserController {
    private final JedisPool jedisPool;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AiUserServiceImpl aiUserService;
    private final AiUserRoleRelationServiceImpl aiUserRoleRelationService;

    public AiUserController(JedisPool jedisPool, BCryptPasswordEncoder bCryptPasswordEncoder, AiUserServiceImpl aiUserService, AiUserRoleRelationServiceImpl aiUserRoleRelationService) {
        this.jedisPool = jedisPool;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.aiUserService = aiUserService;
        this.aiUserRoleRelationService = aiUserRoleRelationService;
    }

    @PostMapping("/saveUser")
    @Operation(summary = "保存用户信息")
    public JsonResult<AiUser> saveUser(@RequestBody AiUser aiUser){
        if (aiUser == null || StringUtils.isBlank(aiUser.getAccount())
                || StringUtils.isBlank(aiUser.getPassword())) {
            return ResultTool.fail(ResultCode.PARAM_NOT_VALID);
        }
        if (UserUtils.existenceAccount(aiUser.getAccount())) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        aiUser.preInsert();
        String password = aiUser.getPassword();
        aiUser.setPassword(bCryptPasswordEncoder.encode(password));
        if (aiUserService.save(aiUser)) {
            AiUserRoleRelation relation = new AiUserRoleRelation();
            relation.setRoleId(aiUser.getRoleId());
            relation.setUserId(aiUser.getId());
            relation.preInsert();
            aiUserRoleRelationService.save(relation);
        } else {
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        return ResultTool.success(aiUser);
    }

    @PostMapping("/delUser")
    @Operation(summary = "删除用户")
    @Parameters(
            @Parameter(name = "id", description = "用户Id", required = true)
    )
    public JsonResult delUser(String id) {
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        LambdaQueryWrapper<AiUser> qw = new LambdaQueryWrapper<>();
        qw.eq(AiUser::getId, id);
        if (aiUserService.remove(qw)) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("user_id", id);
            aiUserRoleRelationService.removeByMap(map);
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }

    @PostMapping("/upUser")
    @Operation(summary = "修改用户")
    public JsonResult<AiUser> upUser(@RequestBody AiUser aiUser){
        if (aiUser==null || StringUtils.isBlank(aiUser.getId()) || StringUtils.isBlank(aiUser.getAccount())) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        aiUser.setPassword(UserUtils.getUser().getPassword());
        if (aiUserService.updateById(aiUser)) {
            AiUserRoleRelation aiUserRole = new AiUserRoleRelation();
            aiUserRole.setUserId(aiUser.getId());
            aiUserRole.setRoleId(aiUser.getRoleId());
            LambdaQueryWrapper<AiUserRoleRelation> guw = new LambdaQueryWrapper<>();
            guw.eq(AiUserRoleRelation::getUserId,aiUser.getId());
            if (aiUserRoleRelationService.update(aiUserRole, guw)) {
                UserUtils.removeUser(aiUser.getAccount());
            }
            return ResultTool.success(aiUser);
        } else {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }

    @PostMapping("/selUserLists")
    @Operation(summary = "查询用户列表")
    public JsonResult<List<AiUser>> selUserLists(@RequestBody AiUser aiUser){
        String userName = aiUser.getUserName();
        if (StringUtils.isBlank(userName)) {
            aiUser.setUserName(null);
        }
        return ResultTool.success(aiUserService.getUserList(aiUser));
    }

    @PostMapping("/selUserInfo")
    @Operation(summary = "查询用户详情")
    @Parameters({
            @Parameter(name = "id", description = "用户Id", required = true)
    })
    public JsonResult<AiUser> selUserInfo(String id){
        if(StringUtils.isBlank(id)){
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        return ResultTool.success(aiUserService.getById(id));
    }

    @PostMapping("/upPassword")
    @Operation(summary = "修改密码")
    @Parameters({
            @Parameter(name = "account", description = "用户账号", required = true),
            @Parameter(name = "oldPassword", description = "旧密码", required = true),
            @Parameter(name = "newPassword", description = "新密码", required = true)
    })
    public JsonResult upPassword(String account, String oldPassword, String newPassword) {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }
        AiUser user = UserUtils.getUser();
        if (StringUtils.isBlank(user.getPassword())) {
            return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        }
        if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword()) && user.getAccount().equals(account)) {
            AiUser gu = new AiUser();
            gu.setPassword(bCryptPasswordEncoder.encode(newPassword));
            LambdaQueryWrapper<AiUser> uw = new LambdaQueryWrapper<>();
            uw.eq(AiUser::getAccount, account);
            aiUserService.update(gu, uw);
            UserUtils.removeUser(account);
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        }
    }

    @PostMapping("/setEnabledUser")
    @Operation(summary = "设置用户是否可用")
    @Parameters({
            @Parameter(name = "account", description = "用户账号", required = true),
            @Parameter(name = "enabled", description = "账号是否可用: 1启用|2禁用", required = true)
    })
    public JsonResult setEnabledUser(String account,EnabledEnum enabled){
        if(StringUtils.isBlank(account) || enabled == null){
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }
        AiUser gu = new AiUser();
        gu.setEnabled(enabled);
        LambdaQueryWrapper<AiUser> uw = new LambdaQueryWrapper<>();
        uw.eq(AiUser::getAccount,account);
        aiUserService.update(gu,uw);
        if(!EnabledEnum.ENABLE.equals(enabled)){
            UserUtils.removeUser(account);
        }
        return ResultTool.success();
    }

    @PostMapping("/setLockUser")
    @Operation(summary = "设置用户是否锁定")
    @Parameters({
            @Parameter(name = "account", description = "用户账号", required = true),
            @Parameter(name = "lock", description = "用户是否锁定: 1可用|2不可用", required = true),
    })
    public JsonResult setLockUser(String account, LockedEnum lock) {
        if (StringUtils.isBlank(account) || lock == null) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }
        AiUser gu = new AiUser();
        gu.setAccountNotLocked(lock);
        LambdaQueryWrapper<AiUser> uw = new LambdaQueryWrapper<>();
        uw.eq(AiUser::getAccount, account);
        aiUserService.update(gu, uw);
        if (!LockedEnum.UN_LOCKED.equals(lock)) {
            UserUtils.removeUser(account);
        }
        return ResultTool.success();
    }

    @PostMapping("/resetPassword")
    @Operation(summary = "重置密码")
    @Parameters({
            @Parameter(name = "account", description = "用户账号", required = true),
            @Parameter(name = "password", description = "重置后的密码", required = true),
    })
    public JsonResult resetPassword(String account,String password){
        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }
        AiUser gu = new AiUser();
        gu.setPassword(bCryptPasswordEncoder.encode(password));
        LambdaQueryWrapper<AiUser> uw = new LambdaQueryWrapper<>();
        uw.eq(AiUser::getAccount,account);
        aiUserService.update(gu,uw);
        UserUtils.removeUser(account);
        return ResultTool.success();
    }

}
