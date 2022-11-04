package com.sensor.modular.authentication.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sensor.modular.authentication.permission.entity.AiPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2022-07-29
 */
@Mapper
public interface AiPermissionMapper extends BaseMapper<AiPermission> {
    /**
     * @param selectListByPath
     */
    List<AiPermission> selectListByPath(String url);
}
