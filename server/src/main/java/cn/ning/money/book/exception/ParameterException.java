package cn.ning.money.book.exception;

import cn.ning.money.book.constant.CodeMsg;


public class ParameterException extends BaseException {
    public ParameterException(String message) {
        super(message);
        super.codeMsg = CodeMsg.PARAMETER_ILLEGAL;
    }

    public ParameterException(CodeMsg codeMsg, String message) {
        super(message);
        super.codeMsg = codeMsg;
    }

    public ParameterException(CodeMsg codeMsg) {
        super(codeMsg.getMessage());
        super.codeMsg = codeMsg;
    }
}
