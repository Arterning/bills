package cn.ning.money.book.service;

import cn.ning.money.book.entity.RoleMenuEntity;
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
public interface RoleMenuService extends IService<RoleMenuEntity> {
    List<RoleMenuEntity> getByRoleId(int id);

    /**
     * 删除角色权限
     */
    void removeByRoleId(Integer roleId);

    /**
     * 批量删除
     */
    int removeByIds(List<Long> ids);

    /**
     * 编辑角色的菜单
     */
    void edit(Integer roleId, List<Integer> menuIds);
}
