package StackPriorityQueue.NO232ImplementQueueusingStacks;

import java.util.Stack;

public class MyQueue2 {

    /**
     * 摊还复杂度分析：
     *时间复杂度最高的操作不会每一次都执行 如果一次复杂的操作会伴随多次简单操作
     * 那么平均下来的时间复杂度也是可以接受的
     *
     * 区别方法一：
     * 我们分析n个元素的push和pop（先push n个 然后在pop n个）
     * 对于方法一：
     * 随着元素的增多 push一次的操作次数增大 push n个元素所需要的总操作次数：
     * 1+2+3x2+4x2+....+nx2=n平方+n--->O(n平方的复杂度)
     * pop n个元素所需要的总操作次数为：n
     *
     * 对比方法二：
     *push n个元素 每次push常数时间 一共用时n
     * pop时 第一次pop需要把stack1元素倒入stack2中 这一次用时2n（stack1 pop 一次 stack2 push 一次 一共n个元素）
     *而后n-1次pop时 都是常数时间 直接stack2.pop
     * 因此总时间： n+(2n)+n-1
     *
     * 可见方法二远优于方法一
     */
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    /**
     *用front来记录队首元素
     */
    private int front;

    MyQueue2() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        if(stack1.isEmpty()){
            front=x;
        }
        stack1.push(x);
    }

    /**
     * 注意 当前stack2中的元素没有完全pop完时 不能把新产生的stack1中的元素加入 会破坏队列先进先出的条件
     * @return
     */
    public int pop() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * peek没有必要把stack1元素倒到stack2中
     * 如果当前stack2没有元素 那么队首元素就是front记录的stack1的最底层元素
     * @return
     */
    public int peek() {
        if(!stack2.isEmpty()){
            return stack2.peek();
        }
        return front;
    }

    public boolean empty() {
        return stack1.isEmpty()&&stack2.isEmpty();
    }


}
