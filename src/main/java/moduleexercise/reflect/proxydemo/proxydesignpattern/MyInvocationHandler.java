package moduleexercise.reflect.proxydemo.proxydesignpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:    通过反射生产 需要被代理的业务类的代理对象 —— 需要实现 InvocationHandler 接口
 * @author: gezx
 * @date: 2021/5/30 23:24
 */
public class MyInvocationHandler implements InvocationHandler {
    // 通过构造方法对该obj属性赋值
    private Object obj;

    public Object bind(Object obj){
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeMethod();
        Object result = method.invoke(this.obj, args);
        afterMethod();
        return result;
    }

    public void beforeMethod(){
        //todo 可以结合业务进行相关业务的增强逻辑处理
        System.out.println(" 该方法可以在动态生成的代理对象 去执行业务实现类中需要被代理的方法 前调用执行，以便达到对业务方法增强的效果 ");
    }

    public void afterMethod(){
        System.out.println(" 该方法可以在动态生成的代理对象 执行完业务实现类中需要被代理的方法 后调用执行，以便达到对业务方法返回值进行增强 ");
    }

}
