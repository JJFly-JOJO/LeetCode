package MultiThreads.NO1114PrintinOrder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/11 15:45
 * @description ---------利用原子性的Integer实现乐观锁--------
 */
public class Foo {

    public static void main(String[] args) {
        Foo f=new Foo();
        Thread t1=new Thread(() -> {
            try {
                f.first(() -> System.out.println("1"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2=new Thread(() -> {
            try {
                f.second(() -> System.out.println("2"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3=new Thread(() -> {
            try {
                f.third(() -> System.out.println("3"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t3.start();
        t2.start();
        t1.start();

    }

    private AtomicInteger firstToSecond = new AtomicInteger(0);

    private AtomicInteger secondToThird = new AtomicInteger(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstToSecond.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstToSecond.intValue() != 1) {
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondToThird.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondToThird.intValue() != 1) {
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}
