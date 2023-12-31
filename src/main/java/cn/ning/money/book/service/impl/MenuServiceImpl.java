package cn.ning.money.book.service.impl;

import cn.ning.money.book.bo.MenuBO;
import cn.ning.money.book.constant.MenuConstants;
import cn.ning.money.book.constant.RoleConstant;
import cn.ning.money.book.entity.MenuEntity;
import cn.ning.money.book.entity.RoleEntity;
import cn.ning.money.book.mapper.MenuMapper;
import cn.ning.money.book.service.MenuService;
import cn.ning.money.book.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public List<MenuEntity> getUserMenus(Long userId) {
        List<MenuEntity> ret;
        RoleEntity role = roleService.getByUserId(userId).stream().
                filter(n -> RoleConstant.ROOT.equals(n.getName())).findFirst().orElse(null);
        if (role == null) {
            ret = menuMapper.queryUserMenus(userId);
        } else {
            ret = getList();
        }
        return ret;
    }

    @Override
    public MenuEntity getById(Long id) {
        return null;
    }

    @Override
    public List<MenuBO> getTreeList() {
        List<MenuEntity> menuDOS = menuMapper.queryAllMenus();
        List<MenuBO> menuBOS = copyFromMenuDos(menuDOS);
        return generatorTree(menuBOS);
    }

    @Override
    public List<MenuEntity> getList() {
        return list();
    }

    @Override
    public List<MenuEntity> getAll() {
        return menuMapper.queryAllMenus();
    }

    @Override
    public List<MenuEntity> getRoleMenus(Long roleId) {
        // 如为root返回所有的权限
        String roleName = roleService.getById(roleId).getName();
        List<MenuEntity> list;
        if(RoleConstant.ROOT.equals(roleName)) {
            list = getList();
        } else {
            list = menuMapper.queryRoleMenus(roleId);
        }
        return list;
    }

    @Override
    public List<MenuBO> copyFromMenuDos(List<MenuEntity> menuDOS) {
        List<MenuBO> voList = new ArrayList<>();
        menuDOS.forEach(n -> {
            MenuBO temp = new MenuBO();
            BeanUtils.copyProperties(n, temp);
            temp.setId(n.getId().intValue());
            if (MenuConstants.OC.equals(n.getOuterChain())) {
                temp.setOuterChain(true);
            }
            voList.add(temp);
        });
        return voList;
    }

    @Override
    public List<MenuBO> generatorMenuTree(List<MenuBO> boList) {
        return generatorTree(boList);
    }

    @Override
    public void reset(Integer id) {
        menuMapper.notDelete(Long.valueOf(id));
    }

    private List<MenuBO> generatorTree(List<MenuBO> voList) {
        List<MenuBO> tree = list2Tree(voList, null);
        sortTree(tree);
        return tree;
    }

    /**
     * list转为树结构
     */
    private List<MenuBO> list2Tree(List<MenuBO> list, Integer pId) {
        List<MenuBO> tree = new ArrayList<>();
        Iterator<MenuBO> it = list.iterator();
        while (it.hasNext()) {
            MenuBO m = it.next();
            if (m.getParentId() == pId) {
                tree.add(m);
                // 已添加的元素删除掉
                it.remove();
            }
        }
        // 寻找子元素
        tree.forEach(n -> n.setChildren(list2Tree(list, n.getId())));
        return tree;
    }

    /**
     * 排序树结构中的同级元素
     */
    private void sortTree(List<MenuBO> list) {
        // 当前层级元素的个数大于1才需要比较
        if (list.size() > 1) {
            // 冒泡排序
            for (int i=0; i<list.size() -1; i++) {
                for (int j=0; j<list.size()- i -1; j++) {
                    if (list.get(j).getOrderNo() == null || list.get(j).getOrderNo() > list.get(j + 1).getOrderNo()) {
                        // 交换
                        Collections.swap(list, j, j + 1);
                    }
                }
            }
        }
        // 则继续向下
        list.stream().filter(n -> n.getChildren() != null).forEach(n -> sortTree(n.getChildren()));
    }
}
