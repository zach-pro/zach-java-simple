package com.zach.modular.authentication.request_path.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zach.modular.authentication.request_path.entity.AiRequestPath;

/**
 * <p>
 * 请求路径 服务类
 * </p>
 *
 * @author apple
 * 
 */
public interface AiRequestPathService extends IService<AiRequestPath> {
    /**
     * @param tagName 查询当前请求路径的序号
     * @return
     */
    Integer selTagNextRank(String tagName);
}
