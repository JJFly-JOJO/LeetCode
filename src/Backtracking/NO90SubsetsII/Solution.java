package Backtracking.NO90SubsetsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = new Solution().subsetsWithDupForSolution2(nums);
        System.out.println(result);
    }

    //回溯法 首先将数组进行排序
    //错误解法 仅仅是找到链表 没有考虑断链组合的情况
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);//Arrays工具类(对数组进行操作)中的排序方法 升序排序
        ArrayList<Integer> tempArray = new ArrayList<>();
        backTacking(0, nums, result, tempArray);
        //以每个数组头元素为链递归向下寻找
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                continue;
            backTacking2(i, nums, result, tempArray);
        }

        return result;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);//<----------------------!!!!!!!!!!!!!!!!!!!!!一定要先排序
        backTackingForAns(0, nums, result, new ArrayList<Integer>());
        return result;

    }

    private void backTackingForAns(int curIndex, int[] nums, List<List<Integer>> result, ArrayList<Integer> temp) {
        result.add(new ArrayList<>(temp));
        for (int i = curIndex; i < nums.length; i++) {
            if (i > curIndex && nums[i] == nums[i - 1])//--------------------！！！！！！！！！i>curIndex  curIndex作为标识头
                continue;
            temp.add(nums[i]);
            backTackingForAns(i + 1, nums, result, temp);
            temp.remove(temp.size() - 1);
        }
    }

    private void backTacking(int curIndex, int[] nums, List<List<Integer>> result, ArrayList<Integer> temp) {
        result.add(new ArrayList<>(temp));
        if (curIndex >= nums.length) {
            return;
        }
        for (int i = curIndex; i < nums.length; i++) {
            temp.add(nums[curIndex]);
            backTacking(curIndex + 1, nums, result, temp);
            temp.remove(temp.size() - 1);
        }
    }

    private void backTacking2(int curIndex, int[] nums, List<List<Integer>> result, ArrayList<Integer> temp) {
        if (curIndex >= nums.length)
            return;
        for (int i = curIndex; i < nums.length; i++) {
            temp.add(nums[curIndex]);
            result.add(new ArrayList<>(temp));
            backTacking2(curIndex + 1, nums, result, temp);
            temp.remove(temp.size() - 1);
        }
    }

    //解法二 每一次加入元素都是在原来的集合上增加新的元素后的新的集合
    //注意对内存的限制！！！！！！
    public List<List<Integer>> subsetsWithDupForSolution2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();//第一次为空元素集合
        result.add(new ArrayList<Integer>());//初始化空数组
        int index = 0;//记录上一次更新的集合下标
        Arrays.sort(nums);//一定要排序
        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                //元素相同的情况 只对上一次新加入的元素集合中再加入新元素
                int j = index;
                index = result.size();//记录更新前的下标索引
                int size = result.size();
                for (; j < size; j++) {
                    ArrayList<Integer> tempArray = new ArrayList<>(result.get(j));
                    tempArray.add(nums[i]);
                    result.add(tempArray);
                }

            } else {
                index = result.size();//记录更新前的下标
                int size = result.size();
                //元素不同 之前结果集每一个集合加上当前元素
                for (int j = 0; j < size; j++) {//for(int j=0;j<result.size();j++) 错误写法 result的大小是在动态增加的 这样会造成死循环而出现堆空间溢出
                    ArrayList<Integer> tempArray = new ArrayList<>(result.get(j));
                    tempArray.add(nums[i]);
                    result.add(tempArray);
                }
            }
        }
        return result;
    }

    //解法三 位操作
    public List<List<Integer>> subsetsWithDupForSolution3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);//排好序是前提
        int subsetNum = 1 << nums.length;
        for (int i = 0; i < subsetNum; i++) {
            List<Integer> temp = new ArrayList<>();
            boolean illegal = false;
            for (int j = 0; j < nums.length; j++) {
                if ((i >> j & 1) == 1) {
                    if (j > 0 && nums[j] == nums[j - 1] && (i >> (j - 1) & 1) != 1) {
                        //如果重复元素标志不是11 则当前组合已经重复
                        illegal = true;
                        break;
                    } else {
                        temp.add(nums[j]);
                    }
                }
            }
            if (!illegal)
                result.add(temp);
        }
        return result;
    }

}
