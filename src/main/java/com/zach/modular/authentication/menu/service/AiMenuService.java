package com.zach.modular.authentication.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zach.modular.authentication.menu.entity.AiMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author apple
 * 
 */
public interface AiMenuService extends IService<AiMenu> {
    List<AiMenu> selMenuByRoleId(String roleId);
    AiMenu selectMenuOne(String id);
    List<AiMenu> selectSubclassMenu();
    List<AiMenu> selParentMenuLists();
}
