package Interview;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/25 23:42
 * @description
 */
public class OddEven {

    private int n;

    private volatile int count = 1;

    private Object lock = new Object();

    public OddEven(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        OddEven oe = new OddEven(100);
        Thread t1 = new Thread(() -> {
            try {
                oe.printEven();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                oe.printOdd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }

    public void printOdd() throws InterruptedException {
        for (; count <= n; ) {
            synchronized (lock) {
                while ((count & 1) != 1) {
                    lock.wait();
                }
                if (count <= n) {
                    System.out.println(count++);
                }
                lock.notify();
            }
        }
    }

    public void printEven() throws InterruptedException {
        for (; count <= n; ) {
            synchronized (lock) {
                while ((count & 1) != 0) {
                    lock.wait();
                }
                if (count <= n) {
                    System.out.println(count++);
                }
                lock.notify();
            }
        }
    }

}
