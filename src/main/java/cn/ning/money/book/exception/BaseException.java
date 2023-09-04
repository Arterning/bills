package cn.ning.money.book.exception;

import cn.ning.money.book.constant.CodeMsg;

public class BaseException extends RuntimeException{
    protected CodeMsg codeMsg;

    public BaseException(String message) {
        super(message);
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
