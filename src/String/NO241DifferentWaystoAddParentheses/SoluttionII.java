package String.NO241DifferentWaystoAddParentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/28 12:00
 * @description -----------动态规划解法-----------
 */
public class SoluttionII {

    public static void main(String[] args) {
        System.out.println(new SoluttionII().diffWaysToCompute("2*3-4*5"));
    }

    public List<Integer> diffWaysToCompute(String input) {
        //预处理数据
        List<Integer> nums = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        char[] chars = input.toCharArray();
        int num = 0;
        for (char c : chars) {
            if (isOperator(c)) {
                nums.add(num);
                num = 0;
                operators.add(c);
            } else {
                num = num * 10 + c - '0';
            }
        }
        nums.add(num);
        List<Integer>[][] dp = new List[nums.size()][nums.size()];
        //initialize
        //初始化规模为1的dp
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums.get(i));
            dp[i][i] = list;
        }
        //dp
        for (int s = 2; s <= nums.size(); s++) {
            //子问题为 子串的规模
            int last = nums.size() - s;
            for (int i = 0; i <= last; i++) {
                List<Integer> res = new ArrayList<>();
                int subLast = i + s - 1;
                for (int j = i; j < subLast; j++) {
                    //枚举当前j下对应的是最后一个操作：(  ) op[j] (  )
                    List<Integer> leftDp = dp[i][j];
                    List<Integer> rightDp = dp[j + 1][subLast];
                    for (int l : leftDp) {
                        for (int r : rightDp) {
                            res.add(calculate(l, r, operators.get(j)));
                        }
                    }
                }
                dp[i][subLast] = res;
            }
        }
        return dp[0][nums.size() - 1];
    }

    private int calculate(int l, int r, char c) {
        if (c == '+') {
            return l + r;
        }
        if (c == '-') {
            return l - r;
        }
        return l * r;
    }


    private boolean isOperator(int c) {
        return c == '+' || c == '-' || c == '*';
    }

}
