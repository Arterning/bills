package cn.ning.money.book.service.impl;

import cn.ning.money.book.bo.PageBO;
import cn.ning.money.book.constant.RoleConstant;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.entity.UserRoleEntity;
import cn.ning.money.book.mapper.UserMapper;
import cn.ning.money.book.service.RoleService;
import cn.ning.money.book.service.UserRoleService;
import cn.ning.money.book.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserEntity getByName(String userName) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public UserEntity getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public PageBO<UserEntity> getByPage(String username, Boolean deleted, Date date, int pageIndex, int pageSize) {
        int total = userMapper.queryTotal(username, deleted, date);
        List<UserEntity> list = userMapper.queryByPage(username, deleted, date, pageIndex * pageSize, pageSize);
        return new PageBO<>(list, total);
    }

    @Transactional
    @Override
    public void edit(Integer id, String nickname, Integer sex, String email) {
        // 更新用户
        UserEntity user = new UserEntity();
        user.setId(id.longValue());
        user.setNickname(nickname);
        user.setSex(sex);
        user.setEmail(email);
        userMapper.updateById(user);
    }

    @Override
    public void add(String username, String nickname, Integer sex, String email, String credential) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setSex(sex);
        user.setEmail(email);
        user.setCredential(new BCryptPasswordEncoder().encode(credential));
        save(user);
    }

    @Override
    public void reset(Integer id) {
        userMapper.notDelete(Long.valueOf(id));
    }

    @Transactional
    @Override
    public void saveWithDefaultRole(UserEntity userDO) {
        // 保存用户
        save(userDO);
        // 找到默认角色Id
        Long roleId = roleService.getByName(RoleConstant.DEFAULT).getId();
        // 保存默认角色
        userRoleService.save(new UserRoleEntity(userDO.getId().intValue(), roleId.intValue()));
    }
}
