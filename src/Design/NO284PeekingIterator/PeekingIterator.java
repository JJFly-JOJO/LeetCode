package Design.NO284PeekingIterator;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {

    /**
     * 有点类似包装的设计模式 对迭代器功能进行增强（peek功能）
     * 由于迭代器只有hasNext和next功能 如果用next 那这个元素就已经弹出 不能再peek到
     * 因此可以采用预取----------------------把next出来的元素单独保存
     *
     * @param iterator
     */
    private Iterator<Integer> iterator;
    private Integer cur;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        //cur=iterator.next();
    }

    public Integer peek() {
        if (cur != null) {
            return cur;
        }
        cur = iterator.next();
        return cur;
    }

    @Override
    public boolean hasNext() {
        if (cur == null && !iterator.hasNext()) {
            return false;
        }
        return true;
    }

    @Override
    public Integer next() {
        if (cur != null) {
            try {
                return cur;
            } finally {
                cur = null;
            }
        }
        return iterator.next();
    }
}
