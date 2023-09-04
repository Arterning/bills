package cn.ning.money.book.service;

import cn.ning.money.book.bo.PageBO;
import cn.ning.money.book.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 根据用户名获取用户
     */
    UserEntity getByName(String userName);

    /**
     * 根据Id获取指定用户
     */
    UserEntity getById(Long id);

    /**
     * 获取所有用户
     */
    List<UserEntity> getUsers();

    /**
     * 分页获取用户
     */
    PageBO<UserEntity> getByPage(String username, Boolean deleted, Date date, int pageIndex, int pageSize);

    void edit(Integer id, String nickname, Integer sex, String email);

    void add(String username, String nickname, Integer sex, String email, String credential);

    void reset(Integer id);

    /**
     * 保存用户并添加默认角色
     */
    void saveWithDefaultRole(UserEntity userDO);
}
