package cn.springAop.aopchain;

/**
 * Created by zhangyu on 2017/11/2.
 */
public abstract  class ChainHandler {

    public void execute(Chain chain) {

        handleProcess();
        chain.proceed();

    }

    public abstract  void handleProcess();

}
