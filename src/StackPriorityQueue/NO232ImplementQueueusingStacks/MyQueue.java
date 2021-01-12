package StackPriorityQueue.NO232ImplementQueueusingStacks;

import java.util.Stack;

public class MyQueue {
    /**
     * 方法一 处理入队操作 出队O(1)常数复杂度
     */

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * 时间复杂度
     * O(2n)
     *
     * @param x
     */
    public void push(int x) {
        if (stack1.isEmpty()) {
            stack1.push(x);
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            //后进后出
            stack1.push(x);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
    }

    public int pop() {
        return stack1.pop();
    }

    public int peek() {
        return stack1.peek();
    }

    public boolean empty() {
        return stack1.isEmpty();
    }

}
