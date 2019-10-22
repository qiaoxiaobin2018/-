package cn.itcast.cstm.Dao;

public class CustomerException extends Exception{
    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(Throwable cause) {
        super(cause);
    }

    protected CustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
