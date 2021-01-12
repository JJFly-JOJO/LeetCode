package MultiThreads.NO1114PrintinOrder;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/11 16:58
 * @description
 */
public class FooIII {

    private int num;
    private ReentrantLock rLock;
    private Condition c1;
    private Condition c2;
    private Condition c3;

    public FooIII() {
        num = 1;
        rLock = new ReentrantLock();
        c1 = rLock.newCondition();
        c2 = rLock.newCondition();
        c3 = rLock.newCondition();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        rLock.lock();
        try {
            printFirst.run();
        } finally {
            rLock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {
        printThird.run();
    }
}
