package cn.ning.money.book.mapper;

import cn.ning.money.book.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuMapper extends BaseMapper<MenuEntity> {
    List<MenuEntity> queryUserMenus(Long userId);

    List<MenuEntity> queryAllMenus();

    List<MenuEntity> queryRoleMenus(Long roleId);

    void notDelete(Long id);
}
