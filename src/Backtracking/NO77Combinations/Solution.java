package Backtracking.NO77Combinations;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<List<Integer>> result = new Solution().combineForSolution1(4, 2);
        for (List<Integer> temp : result
        ) {
            System.out.println(temp);
        }
    }

    //DFS k作为中断条件之一
    public List<List<Integer>> combineForSolution1(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        //int num = 0;//控制递归层数
        Integer numInteger=0;
        int cur = 1;
        //backTracking(cur, n, k, num, result, new ArrayList<Integer>());
        backTracking2(cur,n,k,numInteger,result,new ArrayList<Integer>());
        return result;
    }

    private void backTracking(int cur, int n, int k, int num, List<List<Integer>> result, List<Integer> temp) {
        //if (++num > k)//!!!!!!!!注意这里不能拿num原生数据类型作为控制变量 因为传入的num为临时变量 返回值时不会将当前值返回
            //return;
        if(num>k)
            return;
        for (int i = cur; i <= n; i++) {
            temp.add(i);
            num++;
            backTracking(i + 1, n, k, num, result, temp);
            if (num == k) {
                result.add(new ArrayList<Integer>(temp));
            }
            num--;
            temp.remove(temp.size() - 1);
        }
    }

    private void backTracking2(int cur, int n, int k, Integer num, List<List<Integer>> result, List<Integer> temp) {
        //if (++num > k)//!!!!!!!!注意这里不能拿num原生数据类型作为控制变量 因为传入的num为临时变量 返回值时不会将当前值返回
        //return;
        if(num>k)
            return;
        for (int i = cur; i <= n; i++) {
            temp.add(i);
            num++;
            if(num<k){
                backTracking2(i + 1, n, k, num, result, temp);
            }
            if (num == k) {
                result.add(new ArrayList<Integer>(temp));
            }
            num--;
            temp.remove(temp.size() - 1);
        }
    }
}
