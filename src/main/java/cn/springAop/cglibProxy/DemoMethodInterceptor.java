package cn.springAop.cglibProxy;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangyu on 2017/11/2.
 */
public class DemoMethodInterceptor implements MethodInterceptor {


    /**
     *
     * @param o  要反射的对象
     * @param method
     * @param objects   反射的对象方法的参数
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("before cglib");

        Object result = null;   //作为方法的返回值  因为方法可能有  可能没有

        try {

            result = methodProxy.invokeSuper(o,objects);   //利用反射机制

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw  e;
        }finally {
            System.out.println("after cglib");
        }

        return result;
    }
}
