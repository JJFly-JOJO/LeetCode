package Array.NO253MeetingRoomsII;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/23 17:26
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        //TreeSet test
        int[][] intervals = new int[][]{{1, 5}, {8, 9}, {8, 9}};
        /*TreeSet<int[]> right = new TreeSet<>((o1, o2) -> o1[1] - o2[1]);
        right.addAll(Arrays.asList(intervals));
        right.forEach((o1) -> System.out.println(Arrays.toString(o1)));
        right.remove(intervals[0]);
        right.forEach((o1) -> System.out.println(Arrays.toString(o1)));*/
        System.out.println(new Solution().minMeetingRooms(intervals));
    }

    /**
     * ---------------------错误 该方法存在局限性 TreeMap（平衡二叉树）是无法解决键值重复的情况的[[5,8],[6,8]]------
     *
     * @param intervals
     * @return
     */
    public int minMeetingRoomsErro(int[][] intervals) {
        TreeMap<Integer, int[]> right = new TreeMap<>();
        //右端放入TreeSet
        for (int[] t : intervals) {
            right.put(t[1], t);
        }
        //左端排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int[] t : intervals) {
            Map.Entry<Integer, int[]> temp = right.floorEntry(t[0]);
            if (temp != null) {
                int[] v = temp.getValue();
                right.remove(t[1]);
                right.remove(v[1]);
                v[1] = t[1];
                right.put(v[1], v);
            }
        }
        return right.size();
    }

    /**
     * ---------------------TreeMap无法解决键值重复的问题-----------------
     * 思考：我们重新回顾我们的策略-贪心策略，左端从小到大排序，然后依次插入满足左界限大于等于右界限的最接近左界限的一个值
     * 我们已经把左界限从小到大排序了，也就是说这个满足条件的右界限也一定是最小的那个，那么我们就屏蔽了其他右界限之间的关系
     * 因此我们可以考虑堆结构
     * <p>
     * 思考：我们需要考虑合并后删除堆中的合并元素吗？（当然堆无法实现删除除顶部元素之外的元素）
     * <p>
     * 改进点：我们看到我们的堆中放入的是int[2] 但是int[0]并没有使用 因此我们只需要维护右边界即可
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        for (int[] t : intervals) {
            int[] p = queue.peek();
            if (p != null && t[0] >= p[1]) {
                queue.poll();
                p[1] = t[1];
                queue.add(p);
                continue;
            }
            queue.add(t);
        }
        return queue.size();
    }

    /**
     * ----------------------改进---------------
     *
     * @param intervals
     * @return
     */
    public int minMeetingRoomsPromoted(int[][] intervals) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        for (int[] t : intervals) {
            Integer p = queue.peek();
            if (p != null && t[0] >= p) {
                queue.poll();
            }
            queue.add(t[1]);
        }
        return queue.size();
    }

}
