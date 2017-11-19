package cn.springAop.aopchain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyu on 2017/11/2.
 */
public class ChainClient {


    static class  ChainHandlerA extends ChainHandler{

        public void handleProcess() {
            System.out.println("a");
        }
    }

    static class  ChainHandlerB extends ChainHandler{

        public void handleProcess() {
            System.out.println("b");
        }
    }

    static class  ChainHandlerC extends ChainHandler{

        public void handleProcess() {
            System.out.println("c");
        }
    }

    public static void main(String[] args) {
        List<ChainHandler> handlers = new ArrayList<ChainHandler>();
        handlers.add(new ChainHandlerA());
        handlers.add(new ChainHandlerB());
        handlers.add(new ChainHandlerC());

        Chain chain = new Chain(handlers);
        chain.proceed();
    }

}
