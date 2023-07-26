package cn.ning.money.book.service.impl;

import cn.ning.money.book.entity.UserRoleEntity;
import cn.ning.money.book.mapper.UserRoleMapper;
import cn.ning.money.book.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Transactional
    @Override
    public void edit(Integer userId, List<Integer> roleIds) {
        // 删除用户的所有角色
        userRoleMapper.deleteByUserId(userId);
        // 更新
        List<UserRoleEntity> list = new ArrayList<>();
        roleIds.forEach(n -> {
            UserRoleEntity userRoleDO = new UserRoleEntity();
            userRoleDO.setUserId(userId);
            userRoleDO.setRoleId(n);
            list.add(userRoleDO);
        });
        saveBatch(list);
    }
}
