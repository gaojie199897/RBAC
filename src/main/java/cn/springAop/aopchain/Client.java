package cn.springAop.aopchain;

/**
 * Created by zhangyu on 2017/11/2.
 */
public class Client {


    static class HandlerA extends Handler{

        public void handleProcess() {
            System.out.println("handler a");
        }
    }

    static class HandlerB extends Handler{

        public void handleProcess() {
            System.out.println("handler b");
        }
    }
    static class HandlerC extends Handler{

        public void handleProcess() {
            System.out.println("handler c");
        }
    }
    static class HandlerD extends Handler{

        public void handleProcess() {
            System.out.println("handler d");
        }
    }


    //链式调用
    public static void main(String[] args) {


        Handler handlerA =new HandlerA();
        Handler handlerB =new HandlerB();
        Handler handlerC =new HandlerC();
        Handler handlerD =new HandlerD();

        handlerA.setSuccesser(handlerB);
        handlerB.setSuccesser(handlerC);
        handlerC.setSuccesser(handlerD);
        handlerA.execute();



    }

}
