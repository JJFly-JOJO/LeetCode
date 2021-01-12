package Design.NO346MovingAveragefromDataStream;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/2 17:06
 * @description ------------实现循环数组 类似ArrayDeque 但没有扩容----------
 */
public class MovingAverage2 {

    private int[] window;

    private int head = 0;

    private int count = 0;

    private int sum = 0;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage2(int size) {
        window = new int[size];
    }

    public double next(int val) {
        count++;
        //head指向最后一个节点 也就是上一次加入的节点 head+1指向了头节点 也就是最初加入的
        //技巧-------------由于初始化时值都是0 刚开始窗口没有填满时 减去window[head]并没有影响！
        // 同时 我们以下标0 1 2...作为窗口初始点 都是没有影响的（循环数组）
        head = (head + 1) % window.length;
        sum = sum - window[head] + val;
        window[head] = val;
        return (double) sum / Math.min(count, window.length);
    }

}
