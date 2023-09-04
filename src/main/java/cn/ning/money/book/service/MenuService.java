package cn.ning.money.book.service;

import cn.ning.money.book.bo.MenuBO;
import cn.ning.money.book.entity.MenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface MenuService extends IService<MenuEntity> {

    /**
     * 获取指定用户的权限
     */
    List<MenuEntity> getUserMenus(Long userId);

    /**
     * 通过id查找指定的权限
     */
    MenuEntity getById(Long id);

    /**
     * 分页获取权限
     */
    List<MenuBO> getTreeList();

    List<MenuEntity> getList();

    /**
     * 获取所有的菜单权限
     */
    List<MenuEntity> getAll();

    /**
     * 获取角色权限
     */
    List<MenuEntity> getRoleMenus(Long roleId);

    List<MenuBO> copyFromMenuDos(List<MenuEntity> menuDOS);

    /**
     * 生成菜单树
     */
    List<MenuBO> generatorMenuTree(List<MenuBO> boList);

    void reset(Integer id);

}
