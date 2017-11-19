package cn.springAop.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhangyu on 2017/11/2.
 * jdk实现动态代理 的方法是实现InvocationHandler接口
 */
public class JdkProxySub implements InvocationHandler {

    //引入动态代理的实现类
    private SubjectImpl subject;


    //强引用，通过构造器引入进来


    public JdkProxySub(SubjectImpl subject) {
        this.subject = subject;
    }

    /**
     *
     * @param proxy    代理对象
     * @param method   反射方法
     * @param args     方法的参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("执行动态d代理的逻辑 before");

        Object result = null;

        try {
         //通过反射机制
         result=  method.invoke(subject,args);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }finally {
            System.out.println("after");
        }

        return result;
    }
}
