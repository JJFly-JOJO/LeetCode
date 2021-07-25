package NC.NO119;

import java.util.ArrayList;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/28 15:13
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 2, 8}, 2));
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (k > input.length || k == 0) {
            return res;
        }
        int idx = findIdx(0, input.length - 1, k, input);
        for (int i = 0; i <= idx; i++) {
            res.add(input[i]);
        }
        return res;
    }

    private int findIdx(int l, int r, int k, int[] input) {
        int idx = find(l, r, input);
        int cnt = idx + 1;
        if (cnt > k) {
            return findIdx(l, idx - 1, k, input);
        } else if (cnt < k) {
            return findIdx(idx + 1, r, k, input);
        }
        return idx;
    }

    private int find(int l, int r, int[] input) {
        int mid = (l + r) >>> 1;
        int val = input[mid];
        int j = l;
        swap(j, mid, input);
        for (int i = j + 1; i <= r; i++) {
            if (input[i] <= val) {
                j++;
                swap(j, i, input);
            }
        }
        swap(l, j, input);
        return j;
    }

    private void swap(int a, int b, int[] input) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

}
