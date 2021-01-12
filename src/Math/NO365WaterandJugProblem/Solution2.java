package Math.NO365WaterandJugProblem;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/18 21:54
 * BFS解法-由于栈空间的限制 BFS不会造成栈溢出
 */
public class Solution2 {

    private int capacityX;
    private int capacityY;

    public static void main(String[] args) {
        //System.out.println(new Solution().canMeasureWater(104579, 104593, 12444));
        System.out.println(new Solution2().canMeasureWater(2, 0, 2));
    }

    /**
     * BFS思路 找到当前state的所有可能的nextState 入队 然后继续向下搜索
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater(int x, int y, int z) {
        //特判
        if (z == 0) {
            return true;
        }
        if (x + y < z) {
            return false;
        }
        //field initialize
        capacityX = x;
        capacityY = y;

        State initState = new State(0, 0);

        //queue for BFS
        Queue<State> queue = new LinkedList<>();
        Set<State> isVisited = new HashSet<>();

        queue.add(initState);
        isVisited.add(initState);

        //BFS
        while (!queue.isEmpty()) {
            State temp = queue.poll();
            if (temp.x + temp.y == z) {
                return true;
            }
            List<State> nextStates = getNextStates(temp.x, temp.y);
            for (State tempState : nextStates) {
                if (!isVisited.contains(tempState)) {
                    queue.add(tempState);
                    isVisited.add(tempState);
                }
            }
        }
        return false;
    }

    private List<State> getNextStates(int curX, int curY) {
        //初始化list时 设置好大小 因为可能情况数量时有限的 防止数组动态扩容
        List<State> nextStates = new ArrayList<>(6);
        //x倒给y y不能满
        int temp = capacityY - curY;
        if (temp > 0) {
            if (temp < curX) {
                nextStates.add(new State(curX - temp, capacityY));
            } else {
                nextStates.add(new State(0, curX + curY));
            }
        }
        //y倒给x x不能满
        temp = capacityX - curX;
        if (temp > 0) {
            if (temp < curY) {
                nextStates.add(new State(capacityX, curY - temp));
            } else {
                nextStates.add(new State(curX + curY, 0));
            }
        }
        //x自己补满 x自己不满
        if (curX < capacityX) {
            nextStates.add(new State(capacityX, curY));
        }
        //x自己倒空 不能为空
        if (curX > 0) {
            nextStates.add(new State(0, curY));
        }
        //y自己补满
        if (curY < capacityY) {
            nextStates.add(new State(curX, capacityY));
        }
        //y自己倒空
        if (curY > 0) {
            nextStates.add(new State(curX, 0));
        }
        return nextStates;
    }

    class State {
        public int x;
        public int y;

        public State() {
        }

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }
            State state = (State) obj;
            return this.x == state.x && this.y == state.y;
        }
    }

}
