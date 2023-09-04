package cn.ning.money.book.common.enums;

public enum BusinessStatus {
    /**
     * 成功
     */
    SUCCESS(0),

    /**
     * 失败
     */
    FAIL(-1),
    ;

    private int code;

    BusinessStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
