package com.zach.modular.business.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zach.modular.business.example.entity.Example;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author apple
 * @since 2022-11-09
 */
@Mapper
public interface ExampleMapper extends BaseMapper<Example> {

}
