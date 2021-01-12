package WeekContest.SingleWeek.NO207;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 2.0  修改之前错误方法
 * @date 2020/9/20 16:11
 * 思考：我们可以对比之前version 1。0错误的回溯方法
 * ---之前使用的回溯划分成两个子问题后 子问题还要继续划分 但是会出现一个问题 对于abcdef 我们进行子问题划分时
 * （1）a-----bcdef->a-----b-----cdef
 * (2)ab----cdef->a-----b------cded
 * 可以看到这样类似于分支思想的子问题划分（相当于每次二分）得到的结果是重复的 并且也不能解决左子问题递归结束后
 * isVisited弹出遍历的节点 但是右子问题其实会出现重复的节点但是isVisited中没有的情况
 * <p>
 * 再来看下面的回溯方法 从左端开始划分子问题 左端子问题则为最小单元 不再进行划分 对右端继续划分为左端不可划分子问题
 * 以及右端可以继续划分的子问题 这样递归的情况相当于是从左往右递归而不是像分支一样 从中间向两侧递归 这样递归到最右侧
 * 我们也就找到了一个解
 * 图示：
 * 左到右
 * -----
 * - ----
 * - - ---
 * - - - --
 * - - - - -
 * 中间向两边---可以考虑用BFS解决左子问题递归结束后弹出存在的问题（注意该BFS是要queue每层全部弹出）
 * -----
 * --- | --
 * -- || - | - || -
 * - ||| - || - | - || -
 */
public class Solution2V2 {

    private int max = 1;

    /**
     * 由于我们要获得数量最多的子串 那么肯定子串长度约小越好
     * 因此我们可以从s的最左端尝试单个字符切分 当切分到最后一个字符（最右端时）此时为一个解 更新最大子串长度
     *
     * @param s
     * @return
     */
    public int maxUniqueSplit(String s) {
        int res = 1;
        Set<String> isVisited = new HashSet<>();
        DFS(0, 0, res, s, s.length(), isVisited);
        return max;
    }

    private void DFS(int start, int end, int res, String s, int length, Set<String> isVisited) {
        //终止条件 切割到最后一个字符串
        if (end == length - 1) {
            if (!isVisited.contains(s.substring(start, length))) {
                max = Math.max(res, max);
                return;
            } else {
                return;
            }
        }
        //dfs两种情况 向前切割一个字符 或者不切割
        //向前切割一个
        String sub = s.substring(start, end + 1);
        if (!isVisited.contains(sub)) {
            isVisited.add(sub);
            DFS(end + 1, end + 1, res + 1, s, length, isVisited);
            isVisited.remove(sub);
        }
        //或者不切割
        DFS(start, end + 1, res, s, length, isVisited);
    }

}
