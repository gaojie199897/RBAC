package cn.springAop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyu on 2017/11/2.
 * 将这个类声明为我的切面类
 */
@Aspect
@Component    //注入到IOC容器中
public class ObjectAspectConfig {


    /**
     * 切面类中主要包括两大类  切入点@pointcut
     */
    @Pointcut("bean(*service)")    //拦截service结尾的类
    public void matchCondition(){}


    /**
     * 使用表达式  拦截service 下以impl结尾的类中的所有的方法
     * public * *（）
     */
    @Pointcut("execution(public * cn.tms.service..impl.*(..))")
    public void executionMeach(){}

    /**
     * 切面类中主要包括两大类  切入点Advice
     */
    @Before("matchCondition()")
    public void before(){
        System.out.println("$$$$before  前置增强");
    }

}
