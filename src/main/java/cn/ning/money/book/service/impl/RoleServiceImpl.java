package cn.ning.money.book.service.impl;

import cn.ning.money.book.bo.PageBO;
import cn.ning.money.book.constant.CodeMsg;
import cn.ning.money.book.entity.RoleEntity;
import cn.ning.money.book.exception.BusinessException;
import cn.ning.money.book.mapper.RoleMapper;
import cn.ning.money.book.service.RoleMenuService;
import cn.ning.money.book.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<RoleEntity> getByUserId(Long userId) {
        return roleMapper.queryByUserId(userId);
    }

    @Override
    public List<RoleEntity> getRoles() {
        return roleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public PageBO<RoleEntity> getByPage(String name, Boolean deleted, Date date, int pageIndex, int pageSize) {
        int total = roleMapper.queryTotal(name, deleted, date);
        List<RoleEntity> list = roleMapper.queryByPage(name, deleted, date, pageIndex * pageSize, pageSize);
        return new PageBO<>(list, total);
    }

    @Override
    public boolean add(String name, String info) {
        RoleEntity roleDO = new RoleEntity();
        roleDO.setName(name);
        roleDO.setInfo(info);
        return roleMapper.insert(roleDO) > 0;
    }

    @Transactional
    @Override
    public void edit(Long id, String name, String info, List<Integer> menuIds) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id", id);
        queryWrapper.eq("name", name);
        if (roleMapper.selectOne(queryWrapper) != null) {
            throw new BusinessException(CodeMsg.ROLE_NAME_EXIST);
        }
        RoleEntity roleDO = new RoleEntity(id, name, info);
        roleMapper.updateById(roleDO);
        if (menuIds.size() != 0) {
            roleMenuService.edit(id.intValue(), menuIds);
        }
    }

    @Override
    public void reset(Integer id) {
        roleMapper.notDelete(Long.valueOf(id));
    }

    @Override
    public RoleEntity getByName(String name) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return roleMapper.selectOne(queryWrapper);
    }
}
