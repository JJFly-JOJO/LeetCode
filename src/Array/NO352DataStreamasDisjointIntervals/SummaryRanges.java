package Array.NO352DataStreamasDisjointIntervals;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/23 15:43
 * @description 思路：
 * ----------------使用链表结构来维护区间信息 这样我们合并区间时只需要O(1)时间对链表进行操作
 * ----------------如果用数组 那么区间合并元素减少 数组要整体移动 花费的时间为O(n)
 */
public class SummaryRanges {

    /**
     * 用TreeMap来实现字典以及二分查找
     * key：存放区间的右边界
     */
    private TreeMap<Integer, Node> rightToNode;
    /**
     * count没有算入哨兵节点
     */
    private int count = 0;

    /**
     * Initialize your data structure here.
     */
    public SummaryRanges() {
        //技巧！--------------题目描述 数据流都是非负整数 因此我们初始化我们的treeMap时 我们可以增加一个哨兵
        rightToNode = new TreeMap<>();
        rightToNode.put(-2, new Node(-2, -2, null));
    }

    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("1", "1");
        System.out.println(treeMap.get("1"));
        treeMap.put("1", "2");
        System.out.println(treeMap.get("1"));
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);
        summaryRanges.addNum(3);
        summaryRanges.addNum(2);
        summaryRanges.addNum(2);
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
    }

    public void addNum(int val) {
        Node pre = rightToNode.lowerEntry(val).getValue();
        //cur的右边界是大于val的
        Node cur = pre.next;
        if (cur != null && val >= cur.left) {
            return;
        }
        if (cur == null) {
            //技巧！--------------cur不存在 我们先创建一个 目的是为了减少特殊情况讨论的分支 思路同哨兵思路一样 也是创建一个不可能合并的区间
            cur = new Node(val + 2, val + 2, null);
        }
        if (pre.right + 1 == val) {
            if (val + 1 == cur.left) {
                //易错！----------------------------------这里需要找到pre的上一个节点 让上一个
                //左右区间与cur合并
                rightToNode.remove(pre.right);
                //思考！-----------------------------------合并区间并且移动集合 这里体现了链表插入删除操作只需要O(1)时间的优点 但是查找只能遍历
                pre.right = cur.right;
                pre.next = cur.next;
                //基础！-----------------------------------treeNode存放相同键值key时 value会被覆盖
                rightToNode.put(cur.right, pre);
                count--;
            } else {
                //cur只与左区间合并
                rightToNode.remove(pre.right);
                pre.right = val;
                //易错！---------------------------------这里链表连接一定是连接的pre.next而不是cur 因为cur可能是我们创建出来的哨兵
                rightToNode.put(val, pre);
            }
        } else if (val + 1 == cur.left) {
            //cur只与右区间合并 注意前面的左右区间合并一定要return
            cur.left = val;
        } else {
            //cur单独成一个区间
            Node node = new Node(val, val, pre.next);
            rightToNode.put(val, node);
            pre.next = node;
            count++;
        }
    }

    public int[][] getIntervals() {
        //找到哨兵头节点
        Node head = rightToNode.get(-2).next;
        int[][] res = new int[count][2];
        int i = 0;
        while (head != null) {
            res[i][0] = head.left;
            res[i++][1] = head.right;
            head = head.next;
        }
        return res;
    }

    /**
     * Node维护区间信息 以及下一个node
     */
    class Node {
        public int left;
        public int right;
        public Node next;

        public Node(int l, int r, Node n) {
            left = l;
            right = r;
            next = n;
        }
    }
}
