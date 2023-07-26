package cn.ning.money.book.bo;

import cn.ning.money.book.entity.MenuEntity;
import cn.ning.money.book.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class UserMenuBO {

    /**
     * 用户类
     */
    private UserEntity userDO;

    /**
     * 权限列表
     */
    private List<MenuEntity> menuDOList;

}
