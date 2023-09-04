package cn.ning.money.book.exception;

import cn.ning.money.book.constant.CodeMsg;


public class NotFoundException  extends BaseException {
    public NotFoundException(String message) {
        super(message);
        super.codeMsg = CodeMsg.NOT_FIND_DATA;
    }

    public NotFoundException(CodeMsg codeMsg,String message) {
        super(message);
        super.codeMsg = codeMsg;
    }
}
