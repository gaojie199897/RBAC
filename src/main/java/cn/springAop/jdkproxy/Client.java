package cn.springAop.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * Created by zhangyu on 2017/11/2.
 */
public class Client {


    public static void main(String[] args) {

        //new ProxyInstance 生成代理类的实现类
        Subject subject = (Subject)Proxy.newProxyInstance(Client.class.getClassLoader(),
                new Class[]{Subject.class},new JdkProxySub(new SubjectImpl()));
        subject.hello();
    }

}
