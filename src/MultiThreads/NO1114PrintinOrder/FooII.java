package MultiThreads.NO1114PrintinOrder;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/11 16:36
 * @description ---------使用synchronized 重量级锁 以及wait notify实现屏障-----------
 */
public class FooII {

    private boolean firstToSecond=false;
    private boolean secondToThird=false;
    Object lock=new Object();

    public FooII() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock){
            printFirst.run();
            firstToSecond=true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock){
            while(!firstToSecond){
                lock.wait();
            }
            printSecond.run();
            secondToThird=true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock){
            while(!secondToThird){
                lock.wait();
            }
            printThird.run();
        }
    }

}
