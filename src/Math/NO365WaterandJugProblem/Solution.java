package Math.NO365WaterandJugProblem;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/18 16:18
 * <p>
 * 思路一：采用DFS 但是此方法存在内存超出限制的情况
 */


class Pair {
    public Integer x;
    public Integer y;

    public Pair() {
    }

    public Pair(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Pair(Pair p) {
        this.x = p.x;
        this.y = p.y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (x != null ? !x.equals(pair.x) : pair.x != null) return false;
        return y != null ? y.equals(pair.y) : pair.y == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}

public class Solution {

    static private Pair tempPair = new Pair();
    private int temp = 0;
    private int target = 0;
    private int capacityX;
    private int capacityY;
    private Set<Pair> isVisited;

    public static void main(String[] args) {
        Set<Pair> set = new HashSet<>();
        set.add(new Pair(1, 0));
        set.add(new Pair(0, 1));
        System.out.println(set.size());
        System.out.println(new Solution().canMeasureWater(104579, 104593, 12444));
    }

    static private void copyPair(int curX, int curY) {
        Solution.tempPair.x = curX;
        Solution.tempPair.y = curY;
    }

    public boolean canMeasureWater(int x, int y, int z) {
        capacityX = x;
        capacityY = y;
        target = z;
        if (x + y == z) {
            //之后x y都不满的时候 不考虑把x y都装满
            return true;
        }
        isVisited = new HashSet<>();
        isVisited.add(new Pair(0, 0));
        isVisited.add(new Pair(capacityX, capacityY));
        if (DFS(x, 0)) {
            return true;
        } else if (DFS(0, y)) {
            return true;
        }
        return false;
    }

    /**
     * 尽量对DFS进行剪枝 减少递归层数 否则会出现栈溢出
     *
     * @param curX
     * @param curY
     * @return
     */
    private boolean DFS(int curX, int curY) {
        if (curX + curY == target) {
            return true;
        }
        copyPair(curX, curY);
        if (isVisited.contains(tempPair)) {
            return false;
        }
        //加入遍历集合
        isVisited.add(new Pair(tempPair));
        //x倒给y y不能满
        temp = capacityY - curY;
        if (temp > 0) {
            if (temp < curX) {
                if (DFS(curX - temp, capacityY)) {
                    //注意易错点：if (temp < curX&&DFS(curX - temp, capacityY))不能写成此
                    // 这样会造成temp<curX（DFS返回false）的情况进入了下一个分支（temp>=curX）
                    return true;
                }
            } else if (DFS(0, curY + curX)) {
                return true;
            }
        }
        //y倒给x x不能满
        temp = capacityX - curX;
        if (temp > 0) {
            if (temp < curY) {
                if (DFS(capacityX, curY - temp)) {
                    return true;
                }
            } else if (DFS(curX + curY, 0)) {
                return true;
            }
        }
        //x自己补满 x自己不满
        if (curX < capacityY && DFS(capacityX, curY)) {
            return true;
        }
        //x自己倒空 不能为空
        if (curX > 0 && DFS(0, curY)) {
            return true;
        }
        //y自己补满
        if (curY < capacityY && DFS(curX, capacityY)) {
            return true;
        }
        //y自己倒空
        if (curY > 0 && DFS(curX, 0)) {
            return true;
        }
        return false;
    }

}
