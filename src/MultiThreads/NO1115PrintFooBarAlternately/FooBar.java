package MultiThreads.NO1115PrintFooBarAlternately;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/20 12:28
 * @description
 */
public class FooBar {

    private int n;

    private volatile int count = 0;

    private Object lock = new Object();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (count % 2 != 0) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                count++;
                lock.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (count % 2 != 1) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                count++;
                lock.notify();
            }
        }
    }

}
