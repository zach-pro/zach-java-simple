package com.sensor.modular.authentication.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sensor.modular.authentication.menu.entity.AiMenu;
import com.sensor.modular.authentication.menu.mapper.AiMenuMapper;
import com.sensor.modular.authentication.menu.service.AiMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author apple
 * @since 2022-07-28
 */
@Service
public class AiMenuServiceImpl extends ServiceImpl<AiMenuMapper, AiMenu> implements AiMenuService {

    @Autowired
    private AiMenuMapper aiMenuMapper;
    @Override
    public List<AiMenu> selMenuByRoleId(String roleId) {
        return aiMenuMapper.selMenuByRoleId(roleId);
    }

    @Override
    public AiMenu selectMenuOne(String id) {
        return aiMenuMapper.selectMenuOne(id);
    }

    @Override
    public List<AiMenu> selectSubclassMenu() {
        return aiMenuMapper.selectSubclassMenuList();
    }
    @Override
    public List<AiMenu> selParentMenuLists() {
        return aiMenuMapper.selParentMenuLists();
    }
}
