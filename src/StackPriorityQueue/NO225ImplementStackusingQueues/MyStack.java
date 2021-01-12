package StackPriorityQueue.NO225ImplementStackusingQueues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 方法一：在push时保证为stack结构 push时先把queue1中所有元素移动到queque2
 * 然后push的元素放入queue1 再把queque2所有元素出队放回来
 * 时间复杂度：O(2n)
 * 方法二:在pop时保证为stack结构 pop时把前n-1个元素移动到queque2 然后queue1中的元素弹出
 * 再把queque2中元素移动到queque1
 * <p>
 * 方法三:一个队列 在pop时 n-1个元素移动到最后 然后poll头元素
 */
public class MyStack {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    //private int last;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        if (queue1.isEmpty()) {
            queue1.add(x);
            while (!queue2.isEmpty()) {
                queue1.add(queue2.poll());
            }
        } else if (queue2.isEmpty()) {
            queue2.add(x);
            while (!queue1.isEmpty()) {
                queue2.add(queue1.poll());
            }
        }
    }

    public int pop() {
        if (!queue1.isEmpty()) {
            return queue1.poll();
        } else if (!queue2.isEmpty()) {
            return queue2.poll();
        }
        return -1;
    }

    public int top() {
        if (!queue1.isEmpty()) {
            return queue1.peek();
        } else if (!queue2.isEmpty()) {
            return queue2.peek();
        }
        return -1;
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

}
