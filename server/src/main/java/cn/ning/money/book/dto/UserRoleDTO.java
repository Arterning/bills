package cn.ning.money.book.dto;

import cn.ning.money.book.entity.RoleEntity;
import lombok.Data;

import java.util.List;


@Data
public class UserRoleDTO {
    private Long id;

    /**
     * 用户名，唯一
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 邮箱
     */
    private String email;

    List<RoleEntity> ownedRoles;

    List<RoleEntity> allRoles;
}
