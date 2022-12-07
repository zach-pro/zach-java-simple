package com.sensor.modular.business.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sensor.modular.business.example.entity.Example;
import com.sensor.modular.business.example.mapper.ExampleMapper;
import com.sensor.modular.business.example.service.ExampleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author apple
 * @since 2022-11-09
 */
@Service
public class ExampleServiceImpl extends ServiceImpl<ExampleMapper, Example> implements ExampleService {

}
