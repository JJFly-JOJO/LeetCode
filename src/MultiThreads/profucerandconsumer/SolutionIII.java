package MultiThreads.profucerandconsumer;

import java.util.concurrent.Semaphore;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/2 2:55 下午
 * @description
 */
public class SolutionIII {

    private static volatile int stockCnt = 0;

    /**
     * 这里不能使用一个Semaphore Semaphore没有界限限制，只能用ArrayBlockingQueue
     *
     * ｜----------->｜ produceS
     * ｜           <｜ consumeS
     *
     * ｜------->    | produceS
     * |        <----| consumeS
     */
    private static Semaphore produceS = new Semaphore(10);
    private static Semaphore consumeS = new Semaphore(0);

    public static void main(String[] args) {
        produceS.release();

        for (int i = 0; i < 5; i++) {
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
        }
    }

    public static class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    produceS.acquire();
                    stockCnt++;
                    System.out.println("producer :" + Thread.currentThread().getName() + "+1 cnt = " + stockCnt);
                    consumeS.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    consumeS.acquire();
                    stockCnt--;
                    System.out.println("consumer :" + Thread.currentThread().getName() + "-1 cnt = " + stockCnt);
                    produceS.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
