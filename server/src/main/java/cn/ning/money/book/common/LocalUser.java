package cn.ning.money.book.common;

import cn.ning.money.book.entity.UserEntity;


public class LocalUser {
    private static final ThreadLocal<UserEntity> local = new ThreadLocal<>();

    /**
     * 得到当前登录用户
     *
     * @return user | null
     */
    public static UserEntity get() {
        return LocalUser.local.get();
    }

    /**
     * 设置登录用户
     */
    public static void set(UserEntity user) {
        LocalUser.local.set(user);
    }

    /**
     * 清理当前用户
     */
    public static void clear() {
        LocalUser.local.remove();
    }
}
