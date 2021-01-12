package TopologicalSort.NO210CourseScheduleII;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ---------------------笔记---------------N0207
 */
public class Solution {

    /**
     * 如果要输出正确的顺序 也就是入度为0的在前 那么首先还是用拓扑排序加BFS解决吧
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int count = 0;
        int[] inDegree = new int[numCourses];
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
            inDegree[temp[0]]++;
            adjacentTable.get(temp[1]).add(temp[0]);
        }

        //queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        //将入度为0初始元素放入队列
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        //BFS
        while (!queue.isEmpty()) {
            result[count++] = queue.poll();
            for (int temp : adjacentTable.get(result[count - 1])) {
                if ((--inDegree[temp]) == 0) {
                    queue.add(temp);
                }
            }
        }
        if (count < numCourses) {
            return new int[0];
        }
        return result;
    }

}
