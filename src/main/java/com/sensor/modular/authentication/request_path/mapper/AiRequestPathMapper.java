package com.sensor.modular.authentication.request_path.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sensor.modular.authentication.request_path.entity.AiRequestPath;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 请求路径 Mapper 接口
 * </p>
 *
 * @author apple
 * @since 2022-07-29
 */
@Mapper
public interface AiRequestPathMapper extends BaseMapper<AiRequestPath> {
    Integer nextRank(String tagName);
}
