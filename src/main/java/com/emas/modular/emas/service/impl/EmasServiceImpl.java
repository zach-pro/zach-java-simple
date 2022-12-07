package com.emas.modular.emas.service.impl;

import com.emas.modular.emas.entity.Emas;
import com.emas.modular.emas.mapper.EmasMapper;
import com.emas.modular.emas.service.EmasService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author apple
 * @since 2022-12-06
 */
@Service
public class EmasServiceImpl extends ServiceImpl<EmasMapper, Emas> implements EmasService {
    private final EmasMapper emasMapper;

    public EmasServiceImpl(EmasMapper emasMapper) {
        this.emasMapper = emasMapper;
    }

    @Override
    public List<Emas> customListSql(String sql) {
        return emasMapper.customListSql(sql);
    }

    @Override
    public Integer customInsertSql(String sql) {
        return emasMapper.customInsertSql(sql);
    }

    @Override
    public Integer customUpdateSql(String sql) {
        return emasMapper.customUpdateSql(sql);
    }

    @Override
    public Integer customDeleteSql(String sql) {
        return emasMapper.customDeleteSql(sql);
    }
}
