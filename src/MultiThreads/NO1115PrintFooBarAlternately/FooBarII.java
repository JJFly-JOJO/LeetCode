package MultiThreads.NO1115PrintFooBarAlternately;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/6 23:33
 * @description
 */
public class FooBarII {

    private int n;
    private LinkedBlockingQueue<Integer> fooQueue = new LinkedBlockingQueue<>(1);
    private LinkedBlockingQueue<Integer> barQueue = new LinkedBlockingQueue<>(1);

    public FooBarII(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //put 队列满 则阻塞
            fooQueue.put(i);
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            barQueue.put(i);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        //take 队列空 阻塞
        for (int i = 0; i < n; i++) {
            barQueue.take();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooQueue.take();
        }
    }

}
