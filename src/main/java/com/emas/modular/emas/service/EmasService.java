package com.emas.modular.emas.service;

import com.emas.modular.emas.entity.Emas;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author apple
 * @since 2022-12-06
 */
public interface EmasService extends IService<Emas> {
    List<Emas> customListSql(String sql);
    Integer customInsertSql(String sql);
    Integer customUpdateSql(String sql);
    Integer customDeleteSql(String sql);
}
