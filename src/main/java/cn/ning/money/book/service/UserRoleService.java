package cn.ning.money.book.service;

import cn.ning.money.book.entity.UserRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
public interface UserRoleService extends IService<UserRoleEntity> {
    void edit(Integer userId, List<Integer> roleIds);
}
