package Divide.NO315CountofSmallerNumbersAfterSelf;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/20 14:36
 * @description -----------int[] index是记录的现下标与原下标之间的对应关系-----------
 */
public class Solution {

    private int[] ans;
    private int[] index;
    private int[] temp;
    /**
     * 离散 将值对应原nums下标i 然后使用map记录现下标与原下标的一个对应
     */
    private int[] tempIndex;

    public static void main(String[] args) {
        System.out.println(new Solution().countSmaller(new int[]{2, 0, 1}).toString());
    }

    public List<Integer> countSmaller(int[] nums) {
        ans = new int[nums.length];
        index = new int[nums.length];
        temp = new int[nums.length];
        tempIndex = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        mergeSort(0, nums.length - 1, nums);
        List<Integer> res = new LinkedList<>();
        for (int n : ans) {
            res.add(n);
        }
        return res;
    }

    private void mergeSort(int l, int r, int[] nums) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >>> 1;
        mergeSort(l, mid, nums);
        mergeSort(mid + 1, r, nums);
        merge(l, mid, r, nums);
    }

    private void merge(int left, int mid, int right, int[] nums) {
        int l = left;
        int r = mid + 1;
        int tIdx = l;
        while (l <= mid && r <= right) {
            if (nums[l] > nums[r]) {
                temp[tIdx] = nums[r];
                tempIndex[tIdx] = index[r];
                r++;
            } else {
                ans[index[l]] += r - mid - 1;
                temp[tIdx] = nums[l];
                tempIndex[tIdx] = index[l];
                l++;
            }
            tIdx++;
        }
        //尾部链接
        while (l <= mid) {
            ans[index[l]] += r - mid - 1;
            temp[tIdx] = nums[l];
            tempIndex[tIdx] = index[l];
            tIdx++;
            l++;
        }
        while (r <= right) {
            temp[tIdx] = nums[r];
            tempIndex[tIdx] = index[r];
            tIdx++;
            r++;
        }
        for (int i = left; i <= right; i++) {
            nums[i] = temp[i];
            index[i] = tempIndex[i];
        }
    }

}
