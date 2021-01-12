package IPCContest.NO1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/12 9:47
 * @description ---输出自然数 1 到 n 所有不重复的排列，即 n 的全排列，要求所产生的任一数字序列中不允许出现重复的数字--
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = Integer.valueOf(scanner.nextLine());
        new Solution().getFullArray(input);
    }

    public void getFullArray(int n) {
        boolean[] isVisited = new boolean[n + 1];
        for(int i=1;i<=n;i++){
            List<Integer> path = new ArrayList<>();
            path.add(i);
            isVisited[i]=true;
            isVisited[i]=false;
        }
    }

}
