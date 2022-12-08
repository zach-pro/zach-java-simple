package com.zach.modular.authentication.request_path.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zach.modular.authentication.request_path.entity.AiRequestPath;
import com.zach.modular.authentication.request_path.mapper.AiRequestPathMapper;
import com.zach.modular.authentication.request_path.service.AiRequestPathService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 请求路径 服务实现类
 * </p>
 *
 * @author apple
 * 
 */
@Service
public class AiRequestPathServiceImpl extends ServiceImpl<AiRequestPathMapper, AiRequestPath> implements AiRequestPathService {
    final AiRequestPathMapper aiRequestPathMap;
    public AiRequestPathServiceImpl(AiRequestPathMapper aiRequestPathMap) {
        this.aiRequestPathMap = aiRequestPathMap;
    }

    @Override
    public Integer selTagNextRank(String tagName) {
        return aiRequestPathMap.nextRank(tagName);
    }
}
