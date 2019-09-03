package org.white.worm.exception;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: BizException.java, v 0.1 2019年09月02日 17:00:00 white Exp$
 */
public class BizException extends RuntimeException {

    private int code;

    private String message;

    public BizException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
