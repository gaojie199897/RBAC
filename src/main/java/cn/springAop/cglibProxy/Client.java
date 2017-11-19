package cn.springAop.cglibProxy;

import cn.springAop.jdkproxy.Subject;
import cn.springAop.jdkproxy.SubjectImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by zhangyu on 2017/11/2.
 */
public class Client {


    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SubjectImpl.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        Subject  subject =(Subject)enhancer.create();
        subject.hello();

    }

}
