package 剑指offer.T2_Singleton;
import org.junit.Test;
public class SingletonTest {
    @Test
    public void testSinglon1() {
        Singleton singlon1 = Singleton.getInstance();
        Singleton singlon2 = Singleton.getInstance();
    }
    @Test
    public void testSingleton2() throws Exception {
        while(true) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Singleton singlon = Singleton.getInstance();
                }
            }).start();
        }
    }
    @Test
    public void testSingletonHungry() throws Exception{
        while(true) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SingletonHungry singletonHungry = SingletonHungry.getInstance();
                }
            }).start ();
        }
    }
    @Test
    public void testSingletonLazy() throws Exception{
        while(true) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SingletonLazy singletonLazy = SingletonLazy.getInstance();
                }
            }).start();
        }
    }



}