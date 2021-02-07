package MultiThreads.NO1115PrintFooBarAlternately;

import java.util.concurrent.Semaphore;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/6 23:56
 * @description
 */
public class FooBarIII {

    Semaphore fooS = new Semaphore(1);
    Semaphore barS = new Semaphore(0);
    private int n;

    public FooBarIII(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooS.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            barS.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barS.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooS.release();
        }
    }

}
