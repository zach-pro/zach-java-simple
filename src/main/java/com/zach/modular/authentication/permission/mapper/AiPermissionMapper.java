package com.zach.modular.authentication.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zach.modular.authentication.permission.entity.AiPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author apple
 * 
 */
@Mapper
public interface AiPermissionMapper extends BaseMapper<AiPermission> {
    /**
     * @param selectListByPath
     */
    List<AiPermission> selectListByPath(String url);
}
