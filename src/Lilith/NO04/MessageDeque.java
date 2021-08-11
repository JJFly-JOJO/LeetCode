package Lilith.NO04;

import java.util.ArrayList;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/11 8:43 下午
 * @description
 */
public class MessageDeque<E> {

    private final Object lock = new Object();

    private volatile int stockIdx = 0;

    private E[] stoks;

    private int MAX_CNT;

    MessageDeque(int maxCnt) {
        MAX_CNT = maxCnt;
        stoks = (E[]) new Object[maxCnt];
    }

    public void addMsg(E e) {
        synchronized (lock) {
            while (stockIdx >= MAX_CNT) {
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            stoks[stockIdx++] = e;
            lock.notifyAll();
        }

    }

    public E saleMsg() {
        E msg;
        synchronized (lock) {
            while (stockIdx <= 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            msg = stoks[--stockIdx];
            lock.notifyAll();
        }
        return msg;
    }

}
