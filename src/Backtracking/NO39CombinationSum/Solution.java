package Backtracking.NO39CombinationSum;


import java.util.*;

public class Solution {
    //剪枝策略
    //递归回溯 从要求的结果往回递推解集组合
    //类比华为软挑 下一个节点必须要大于首节点 排除重复搜索的情况
    //由于candidates内的数 可能存在小的数组成大的数 搜索中可能存在重复结果集的情况
    //首先对candidates数组进行排序 让小的数先进行组合 同层遍历时之前遍历过的小的数就不再选择了 这样可以解决重复解集的情况
    public List<List<Integer>> combinationSumForSolution1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);//首先排好序
        DFS(0, candidates, target, new ArrayDeque<Integer>(), result);//方便从尾部删除元素 这里采用deque结构<------------!!
        return result;
    }

    private void DFS(int begin, int[] candidates, Integer target, ArrayDeque<Integer> tempPath, List<List<Integer>> result) {
        //if(target<0)
        //return;
        if (target == 0) {
            result.add(new ArrayList<>(tempPath));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if ((target - candidates[begin]) < 0) {
                break;//由于元素是从小到大排序 如果当前元素已经让target小于0了 那么之后的元素都不会满足条件 <-----可以考虑将数组从大到小排列!!
            }
            target = target - candidates[i];
            if(target==0){
                tempPath.add(candidates[i]);
                result.add(new ArrayList<>(tempPath));
                tempPath.removeLast();
                target=target+candidates[i];
                break;
            }
            tempPath.add(candidates[i]);
            DFS(i, candidates, target, tempPath, result);
            tempPath.removeLast();
            target = target + candidates[i];
        }
    }
}

class Mycomparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 > o2)//默认o1<o2返回-1
            return -1;
        if (o1 < o2)
            return 1;
        return 0;
    }
}

