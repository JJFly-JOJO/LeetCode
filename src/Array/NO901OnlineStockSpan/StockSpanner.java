package Array.NO901OnlineStockSpan;

import java.util.ArrayDeque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/21 10:31
 * @description
 */
public class StockSpanner {

    ArrayDeque<Integer> stack = new ArrayDeque<>();
    ArrayDeque<Integer> cStack = new ArrayDeque<>();

    public StockSpanner() {

    }

    public int next(int price) {
        int res = 1;
        while (!stack.isEmpty() && price >= stack.peek()) {
            res += cStack.pop();
            stack.pop();
        }
        cStack.push(res);
        stack.push(price);
        return res;
    }

}
