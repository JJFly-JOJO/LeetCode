package MultiThreads.NO1116PrintZeroEvenOdd;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/7 0:23
 * @description
 */
public class ZeroEvenOdd {

    private Semaphore s0 = new Semaphore(1);
    private Semaphore s1 = new Semaphore(0);
    private Semaphore s2 = new Semaphore(0);
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(6);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            s0.acquire();
            printNumber.accept(0);
            if ((i & 1) == 0) {
                s1.release();
            } else {
                s2.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            s1.acquire();
            printNumber.accept(i);
            s0.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            s2.acquire();
            printNumber.accept(i);
            s0.release();
        }
    }

}
