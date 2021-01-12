package TopologicalSort.NO207CourseSchedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ----------------笔记---------------
 */
public class Solution {

    public static void main(String[] args) {
        int[][] pre = new int[][]{{1, 0}};
        System.out.println(new Solution().canFinish(2, pre));
    }

    /**
     * 方法一:拓扑排序之BFS
     * 首先我们要清楚 学一门课程之前要先修另一门课程 所有课程之间以边相连
     * 那么所有课程组成的将是一个有向无权图
     * 关键点：
     * 1.如何构建领接表？
     * （1）我们可以构建一个map(n,List<List>) n表示当前节点 List[0]表示其他节点进入当前节点的集合 List[1]表示当前节点出到其他节点的集合
     * (2) 考虑到此问题 我们要逐步删除入度为0的节点 我们可以只用记录当前节点的所有出节点 再维护一个一维数组来记录所有节点的当前入度数
     * 因为我们并不需要知道当前节点的具体入度节点都有谁 我们只需要知道当前入度数到底变没变为0即可
     *
     * 时间复杂度:O(N+M)遍历一个图需要访问所有的节点和所有的临边 N和M分别为节点数量和临边数量
     * 空间复杂度:O(N+M)空间复杂度主要是建立表所需额外空间adjacentTable有N个节点 储存M条点与点之间的关系
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //记录所有节点的入度数
        int[] inDegree = new int[numCourses];
        //记录入度变为0的节点数
        int count = 0;
        List<List<Integer>> adjacentTable = new ArrayList<List<Integer>>() {
            //注意匿名内部类没有泛型 匿名内部类是外部类的子类 因此外部类必须指明泛型类型
            {
                for (int i = 0; i < numCourses; i++) {
                    add(new ArrayList<>());
                }
            }
        };
        //初始化领接表
        for (int[] temp : prerequisites) {
            inDegree[temp[0]]++;
            adjacentTable.get(temp[1]).add(temp[0]);
        }
        //创建BFS所需队列
        Queue<Integer> queue = new LinkedList<>();
        //将初始0入度的节点加入队列
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                count++;
            }
        }

        //BFS
        while (!queue.isEmpty()) {
            List<Integer> tempArray = adjacentTable.get(queue.poll());
            //遍历当前0入度节点的所有出度节点
            //如果不能确定数组大小（如果数组有size为0的情况）那么就不能使用for(:)
            for (int i = 0; i < tempArray.size(); i++) {
                if (--inDegree[tempArray.get(i)] == 0) {
                    queue.add(tempArray.get(i));
                    count++;
                }
            }
        }
        //拓扑排序结束后 如果count不等于元素个数 说明有环
        if (count < numCourses) {
            return false;
        }
        return true;
    }

    /**
     * 方法二：拓扑排序之DFS
     * 区别方法一的BFS BFS要对整个图遍历结束后才能判断出是否有环（一层一层遍历的弊端）
     * 而我们如果用DFS去判断是否有环 有即可退出 针对只需要判断能否完成课程（返回bool值）的问题情境下 DFS是更好的选择
     * 思路:从初始入度为0的节点开始DFS 节点有三种flag状态 0 1 -1
     * <p>
     * 0和1就是没有遍历 和 当前节点开始时遍历到的节点都为1  如果当前节点已经是1了 此时表示从当前节点出发存在环 直接返回false
     * 关键在于-1的利用：
     * 当DFS递归到最底层时 逐步向外返回时 我们可以把当前遍历到的节点设置为-1 表示当前节点已经变成了上以个节点了 当DFS起始节
     * 点变成了下一个节点 继续DFS时 如果遇到节点为-1的情况说明遇到的这个节点是上一次DFS遍历过的 是不存在环的 这时就不需要再
     * 在当前节点DFS下去了
     * -1也可以理解为 当前节点是可以成功DFS下去 没有环存在的
     *
     * 如果没有-1提供的剪枝策略 最笨的方法 直接每个节点都DFS一次  好一点的方法 只走入度为0的节点 但是任然有重复走路线的问题
     *
     * 注意！！:当图没有一个环时 DFS与BFS两种方法用时是一样的 因为都要全部遍历完所有节点才能得到结果
     *
     * 时间复杂度:O(N+M)遍历一个图需要访问所有的节点和所有的临边 N和M分别为节点数量和临边数量
     * 空间复杂度:O(N+M)空间复杂度主要是建立表所需额外空间adjacentTable有N个节点 储存M条点与点之间的关系
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishForDFS(int numCourses, int[][] prerequisites) {
        //flag
        int[] flag = new int[numCourses];
        //adjTable
        List<List<Integer>> adjacentTable = new ArrayList<List<Integer>>() {
            {
                for (int i = 0; i < numCourses; i++) {
                    add(new ArrayList<>());
                }
            }
        };
        //initialize
        for (int[] temp : prerequisites) {
            adjacentTable.get(temp[1]).add(temp[0]);
        }

        //所有节点 DFS
        for (int i = 0; i < numCourses; i++) {
            if (flag[i] == -1) {
                continue;
            }
            flag[i] = 1;
            for (int temp : adjacentTable.get(i)) {
                if (!DFS(temp, flag, adjacentTable)) {
                    return false;
                }
            }
            //初始层如果成功DFS结束 当前节点也要置-1
            flag[i] = -1;
        }
        return true;
    }

    /**
     * DFS
     *
     * @param temp
     * @param flag
     * @param adjacentTable
     * @return
     */
    private boolean DFS(int temp, int[] flag, List<List<Integer>> adjacentTable) {
        if (flag[temp] == 1) {
            return false;
        }
        if (flag[temp] == -1) {
            return true;
        }
        flag[temp] = 1;
        for (int t : adjacentTable.get(temp)) {
            if (!DFS(t, flag, adjacentTable)) {
                return false;
            }
        }
        flag[temp] = -1;
        return true;
    }
}
