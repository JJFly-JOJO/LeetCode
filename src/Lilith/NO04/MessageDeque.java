package Lilith.NO04;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/11 8:43 下午
 * @description
 */
public class MessageDeque {

    private Object lock = new Object();

    private volatile int stok = 0;

    private int MAX_CNT;

    MessageDeque(int maxCnt) {
        MAX_CNT = maxCnt;
    }

    public void addMsg(int cnt) {
        synchronized (lock) {
            while (stok>=MAX_CNT){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        stok+=cnt;
        lock.notifyAll();
    }

    public void saleMsg(int cnt){
        synchronized (lock){
            while (stok<cnt){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
