package com.sensor.modular.authentication.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sensor.modular.authentication.permission.entity.AiPermission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author apple
 * @since 2022-07-29
 */
public interface AiPermissionService extends IService<AiPermission> {
    List<AiPermission> selectListByPath(String uri);
    void saveRoleAllPermission(String roleId);
    void removeRoleAllPermission(String roleId);

    void saveRequestPathAllPermission(String pathId);
    void removeRequestPathAllPermission(String pathId);
}
