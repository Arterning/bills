package cn.ning.money.book.common;


public class LocalUserId {
    private static final ThreadLocal<Long> local = new ThreadLocal<>();

    /**
     * 得到当前登录用户Id
     *
     * @return userId | null
     */
    public static Long get() {
        return LocalUserId.local.get();
    }

    /**
     * 设置登录用户Id
     */
    public static void set(Long userId) {
        LocalUserId.local.set(userId);
    }

    /**
     * 清理当前用户Id
     */
    public static void clear() {
        LocalUserId.local.remove();
    }
}
