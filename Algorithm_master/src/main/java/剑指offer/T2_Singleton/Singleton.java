package 剑指offer.T2_Singleton;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

//
//public class Singleton {
//    private static Singleton INSTANCE = null;
//
//    public static Singleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (Singleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new Singleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    private Singleton() {
//    }
//}
public class Singleton{
    private static  Singleton INSTANCE=null;
    private Singleton(){
        System.out.println("实例化一个单例");
    }
    public static Singleton getInstance(){
        if(INSTANCE==null){
            synchronized (Singleton.class){
                if(INSTANCE==null){
                    INSTANCE=new Singleton();
                }
//                根本原因是if(instance == null) instance = new Singleton()这两步操作不是原子性的。
            }
        }
        return  INSTANCE;
    }
}

//public class Singleton {
//    private static Singleton instance;
//
//    private Singleton() {
//        System.out.println("实例化了一个新的单例");
//    }
//
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }
//}
//
class SingletonHungry {
    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry() {
        System.out.println("实例化了一个新的单例");
    }

    public static SingletonHungry getInstance() {
        return instance;
    }
}

class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy() {
        System.out.println("实例化了一个新的单例");
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            //为了增加效率，当拥有了实例后，我们直接返回实例。因为拥有了实例后，状态就跟饿汉一致了，显然不会发生线程问题。
            synchronized (SingletonLazy.class) {//因为是静态方法，所以用类做同步锁
                if (instance == null)
                    instance = new SingletonLazy();
            }
        }
        return instance;
    }
}


class Main {
    public static void main(String[] args) {
//        Singleton s=Singleton.INSTANCE;
    }
}
