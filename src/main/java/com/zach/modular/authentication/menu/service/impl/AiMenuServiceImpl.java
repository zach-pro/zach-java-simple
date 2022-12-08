package com.zach.modular.authentication.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zach.modular.authentication.menu.entity.AiMenu;
import com.zach.modular.authentication.menu.mapper.AiMenuMapper;
import com.zach.modular.authentication.menu.service.AiMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author apple
 * 
 */
@Service
public class AiMenuServiceImpl extends ServiceImpl<AiMenuMapper, AiMenu> implements AiMenuService {

    private final AiMenuMapper aiMenuMapper;
    public AiMenuServiceImpl(AiMenuMapper aiMenuMapper) {
        this.aiMenuMapper = aiMenuMapper;
    }

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
