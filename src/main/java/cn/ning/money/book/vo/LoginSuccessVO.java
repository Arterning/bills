package cn.ning.money.book.vo;

import lombok.Data;


@Data
public class LoginSuccessVO {

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

    /**
     * token字符串
     */
    private String token;

    /**
     * 微信登录的openId
     */
    private String openId;
}
