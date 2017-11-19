package cn.springAop.jdkproxy;

/**
 * Created by zhangyu on 2017/11/2.
 */
public class SubjectImpl implements Subject {


    public void findAll() {
        System.out.println("获得所有科目");
    }

    public void hello() {
        System.out.println("helllo");
    }
}
