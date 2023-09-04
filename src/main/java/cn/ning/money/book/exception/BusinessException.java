package cn.ning.money.book.exception;

import cn.ning.money.book.constant.CodeMsg;

public class BusinessException extends BaseException {
    public BusinessException(String message) {
        super(message);
        super.codeMsg = CodeMsg.BUSINESS_ERROR;
    }

    public BusinessException(CodeMsg codeMsg,String message) {
        super(message);
        super.codeMsg = codeMsg;
    }

    public BusinessException(CodeMsg codeMsg) {
        super(codeMsg.getMessage());
        super.codeMsg = codeMsg;
    }
}
