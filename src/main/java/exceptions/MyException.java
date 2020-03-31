package exceptions;

/**
 * @Describe: 创建自定义异常
 * @Author: Gezx
 * @Date: 2020/3/31 23:23
 */
public class MyException extends Exception{  // 创建自定义异常类

    String message; // 定义String类型变量

    public MyException(String ErrorMessagr) { // 父类方法
        message = ErrorMessagr;
    }

    public String getMessage() { // 覆盖getMessage()方法
        return message;
    }
}
