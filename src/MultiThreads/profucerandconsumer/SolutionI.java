package MultiThreads.profucerandconsumer;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/2 10:37 上午
 * @description
 */
public class SolutionI {

    private static final int MAX_STOCK = 10;
    private static final Object LOCK = new Object();
    private static volatile int cnt = 0;

    private static final int CONSUMER_CNT = 5;
    private static volatile int consmCnt = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
        }
    }

    public static class Producer implements Runnable {

        private int produceCnt = 10;

        @Override
        public void run() {
            for (int i = 0; i < produceCnt; i++) {
                synchronized (LOCK) {
                    while (cnt >= MAX_STOCK) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    cnt++;
                    System.out.println("producer :" + Thread.currentThread().getName() + "+1 cnt = " + cnt);
                    LOCK.notifyAll();
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        private int consumeCnt = 10;

        @Override
        public void run() {
            for (int i = 0; i < consumeCnt; i++) {
                synchronized (LOCK) {
                    while (cnt <= 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    cnt--;
                    System.out.println("consumer :" + Thread.currentThread().getName() + "-1 cnt = " + cnt);
                    LOCK.notifyAll();
                }
            }
        }
    }

}
