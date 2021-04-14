package Offer.NO45;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/30 19:39
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minNumber(new int[]{1, 2, 2, 2}));
    }

    public String minNumber(int[] nums) {
        String[] numStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }
        quickSort(numStrs, 0, numStrs.length - 1);
        StringBuilder res = new StringBuilder();
        for (String str : numStrs) {
            res.append(str);
        }
        return res.toString();
    }

    private void quickSort(String[] numStrs, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = getPivot(numStrs, l, r);
        quickSort(numStrs, l, pivot - 1);
        quickSort(numStrs, pivot + 1, r);
    }

    private int getPivot(String[] numStrs, int l, int r) {
        int mid = (l + r) >>> 1;
        String target = numStrs[mid];
        swap(numStrs, l, mid);
        int j = l;
        for (int i = j + 1; i <= r; i++) {
            if (compare(numStrs[i], target) <= 0) {
                j++;
                swap(numStrs, i, j);
            }
        }
        swap(numStrs, j, l);
        return j;
    }

    private int compare(String a, String b) {
        return (a + b).compareTo(b + a);
    }

    private void swap(String[] numStrs, int a, int b) {
        String t = numStrs[a];
        numStrs[a] = numStrs[b];
        numStrs[b] = t;
    }
}
