package com.zach.modular.authentication.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zach.modular.authentication.menu.entity.AiMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author apple
 * 
 */
@Mapper
public interface AiMenuMapper extends BaseMapper<AiMenu> {
    List<AiMenu> selMenuByRoleId(@Param("roleId") String roleId);
    AiMenu selectMenuOne(String id);
    List<AiMenu> selectSubclassMenuList();
    List<AiMenu> selParentMenuLists();
}
