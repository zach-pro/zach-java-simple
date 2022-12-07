package com.emas.modular.emas.mapper;

import com.emas.modular.emas.entity.Emas;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author apple
 * @since 2022-12-06
 */
@Mapper
public interface EmasMapper extends BaseMapper<Emas> {
    List<Emas> customListSql(String sql);
    Integer customInsertSql(String sql);
    Integer customUpdateSql(String sql);
    Integer customDeleteSql(String sql);
}
