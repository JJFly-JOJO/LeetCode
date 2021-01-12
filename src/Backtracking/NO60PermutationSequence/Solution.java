package Backtracking.NO60PermutationSequence;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/28 11:27
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第k个排列
 */
public class Solution {

    private int count = 0;
    private String resStr = null;

    public String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder();
        boolean[] isVisited = new boolean[n + 1];
        if (n == 2) {
            if (k == 1) {
                resStr = "12";
            } else {
                resStr = "21";
            }
        }
        DFS(res, n, 0, k, isVisited);
        return resStr;
    }

    private void DFS(StringBuilder res,
                     int n, int level,
                     int k, boolean[] isVisited) {
        if (resStr != null) {
            return;
        }
        level++;
        for (int i = 1; i <= n; i++) {
            if (resStr != null) {
                return;
            }
            if (isVisited[i]) {
                continue;
            }
            isVisited[i] = true;
            res.append(i);
            if (level == n - 2 || level == n) {
                count += 2;
                if (count - 1 == k) {
                    for (int j = 1; j <= n; j++) {
                        if (!isVisited[j]) {
                            res.append(j);
                        }
                    }
                    resStr = res.toString();
                } else if (count == k) {
                    for (int j = n; j >= 1; j--) {
                        if (!isVisited[j]) {
                            res.append(j);
                        }
                    }
                    resStr = res.toString();
                }
            } /*else if(level==n-1){
                //特例 2 2 此时res中已经加入了1

            }*/ else {
                DFS(res, n, level, k, isVisited);
            }
            res.deleteCharAt(res.length() - 1);
            isVisited[i] = false;
        }
    }

}
