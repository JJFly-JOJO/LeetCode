package StackPriorityQueue.NO155MinStack;

import java.util.Stack;

public class MinStack {

    /**
     * 考虑到要在常数时间获取到最小值！！！！
     * 那么必须在push的时候就要记录这个最小值 让然 也要考虑到出栈时如果当前最小值出栈了 那么次最小值需要能够得到
     * 注意有一个误区
     * 出栈时 如果是最小元素值 也会跟着出栈
     * 这里可以利用双stack实现
     */
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        //push时就要同步跟新minStack
        //stack push同时minStack也push 这样就能保证顺序
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            //pop时两个stack要同时pop
            //包装类型 要用equals比较
            /*if (minStack.peek().equals(stack.peek())) {
                minStack.pop();
            }
            stack.pop();*/
            //优化写法
            if (stack.pop().equals(minStack.peek())) {
                minStack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
