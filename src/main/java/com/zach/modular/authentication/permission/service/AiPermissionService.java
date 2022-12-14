package com.zach.modular.authentication.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zach.modular.authentication.permission.entity.AiPermission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author apple
 * 
 */
public interface AiPermissionService extends IService<AiPermission> {
    List<AiPermission> selectListByPath(String uri);
    void saveRoleAllPermission(String roleId);
    void removeRoleAllPermission(String roleId);

    void saveRequestPathAllPermission(String pathId);
    void removeRequestPathAllPermission(String pathId);
}
