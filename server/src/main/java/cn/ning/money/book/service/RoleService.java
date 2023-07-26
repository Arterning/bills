package cn.ning.money.book.service;

import cn.ning.money.book.bo.PageBO;
import cn.ning.money.book.entity.RoleEntity;
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
public interface RoleService extends IService<RoleEntity> {

    List<RoleEntity> getByUserId(Long userId);

    List<RoleEntity> getRoles();

    PageBO<RoleEntity> getByPage(String name, Boolean deleted, Date date, int pageIndex, int pageSize);

    boolean add(String name, String info);

    void edit(Long id, String name, String info, List<Integer> menuIds);

    void reset(Integer id);

    RoleEntity getByName(String name);
}
