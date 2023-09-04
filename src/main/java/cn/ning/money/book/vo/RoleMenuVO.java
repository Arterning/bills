package cn.ning.money.book.vo;

import cn.ning.money.book.bo.MenuBO;
import cn.ning.money.book.entity.RoleEntity;
import cn.ning.money.book.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class RoleMenuVO {
    private UserEntity user;

    private List<RoleEntity> roles;

    private List<MenuBO> menus;
}
