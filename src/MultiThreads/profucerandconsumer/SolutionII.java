package MultiThreads.profucerandconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/2 11:50 上午
 * @description
 */
public class SolutionII {

    private static final int max_stock = 10;
    private static volatile int stockCnt = 0;

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
        }
    }

    public static class Producer implements Runnable {

        private int pcnt = 10;

        @Override
        public void run() {
            for (int i = 0; i < pcnt; i++) {
                lock.lock();
                try {
                    while (stockCnt >= max_stock) {
                        condition.await();
                    }
                    stockCnt++;
                    System.out.println("producer :" + Thread.currentThread().getName() + "+1 cnt = " + stockCnt);
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        private int ccnt = 10;

        @Override
        public void run() {
            for (int i = 0; i < ccnt; i++) {
                lock.lock();
                try {
                    while (stockCnt <= 0) {
                        condition.await();
                    }
                    stockCnt--;
                    System.out.println("consumer :" + Thread.currentThread().getName() + "-1 cnt = " + stockCnt);
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
