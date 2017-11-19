package cn.springAop.aopchain;

/**
 * Created by zhangyu on 2017/11/2.
 *
 * 责任链调用
 */
public abstract class Handler {


    private Handler successer;



    public void execute(){
        handleProcess();
        if(successer!=null){
            successer.execute();
        }
    }

    public abstract  void handleProcess();


    public Handler getSuccesser() {
        return successer;
    }

    public void setSuccesser(Handler successer) {
        this.successer = successer;
    }
}
