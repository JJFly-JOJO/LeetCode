package Offer.NO30;

import java.util.ArrayDeque;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/14 14:39
 * @description
 */
public class MinStack {

    private ArrayDeque<Integer> stack = new ArrayDeque<>();

    //使用单调栈来保存获取最小元素
    private ArrayDeque<Integer> monoStack = new ArrayDeque<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if (monoStack.isEmpty() || x <= monoStack.peek()) {
            monoStack.push(x);
        }
    }

    public void pop() {
        if (stack.peek().equals(monoStack.peek())) {
            monoStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return monoStack.peek();
    }

}
