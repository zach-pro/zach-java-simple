package com.sensor.modular.authentication.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sensor.modular.authentication.permission.entity.AiPermission;
import com.sensor.modular.authentication.permission.mapper.AiPermissionMapper;
import com.sensor.modular.authentication.permission.service.AiPermissionService;
import com.sensor.modular.authentication.request_path_permission_relation.entity.AiRequestPathPermissionRelation;
import com.sensor.modular.authentication.request_path_permission_relation.mapper.AiRequestPathPermissionRelationMapper;
import com.sensor.modular.authentication.role_permission_relation.entity.AiRolePermissionRelation;
import com.sensor.modular.authentication.role_permission_relation.mapper.AiRolePermissionRelationMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author zyh
 * @since 2022-07-29
 */
@Service
public class AiPermissionServiceImpl extends ServiceImpl<AiPermissionMapper, AiPermission> implements AiPermissionService {
    private final AiPermissionMapper aiPermissionMapper;
    private final AiRolePermissionRelationMapper aiRolePermissionRelationMapper;
    private final AiRequestPathPermissionRelationMapper aiRequestPathPermissionRelationMapper;

    public AiPermissionServiceImpl(AiPermissionMapper aiPermissionMapper, AiRolePermissionRelationMapper aiRolePermissionRelationMapper, AiRequestPathPermissionRelationMapper aiRequestPathPermissionRelationMapper) {
        this.aiPermissionMapper = aiPermissionMapper;
        this.aiRolePermissionRelationMapper = aiRolePermissionRelationMapper;
        this.aiRequestPathPermissionRelationMapper = aiRequestPathPermissionRelationMapper;
    }

    @Override
    public List<AiPermission> selectListByPath(String url) {
        return aiPermissionMapper.selectListByPath(url);
    }

    @Override
    public void saveRoleAllPermission(String roleId) {
        List<AiPermission> permissions = aiPermissionMapper.selectList(new LambdaQueryWrapper<>());
        AiRolePermissionRelation permissionRelation;
        for (AiPermission permission : permissions) {
            permissionRelation = new AiRolePermissionRelation();
            permissionRelation.setRoleId(roleId);
            permissionRelation.setPermissionId(permission.getId());
            permissionRelation.preInsert();
            aiRolePermissionRelationMapper.insert(permissionRelation);
        }
    }

    @Override
    public void removeRoleAllPermission(String roleId) {
        Map<String,Object> map = new HashMap<>(2);
        map.put("role_id",roleId);
        aiRolePermissionRelationMapper.deleteByMap(map);
    }

    @Override
    public void saveRequestPathAllPermission(String pathId) {
        List<AiPermission> permissions = aiPermissionMapper.selectList(new LambdaQueryWrapper<>());
        AiRequestPathPermissionRelation requestPathPermissionRelation;
        for (AiPermission permission : permissions) {
            requestPathPermissionRelation = new AiRequestPathPermissionRelation();
            requestPathPermissionRelation.setUrlId(pathId);
            requestPathPermissionRelation.setPermissionId(permission.getId());
            requestPathPermissionRelation.preInsert();
            aiRequestPathPermissionRelationMapper.insert(requestPathPermissionRelation);
        }
    }

    @Override
    public void removeRequestPathAllPermission(String pathId) {
        Map<String,Object> map = new HashMap<>(2);
        map.put("url_id",pathId);
        aiRequestPathPermissionRelationMapper.deleteByMap(map);
    }
}
