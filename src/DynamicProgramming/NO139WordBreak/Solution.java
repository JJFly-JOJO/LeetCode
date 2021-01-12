package DynamicProgramming.NO139WordBreak;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public boolean wordBreakSlow(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        StringBuilder str = new StringBuilder(s);
        int length = str.length();

        boolean[] dp = new boolean[length + 1];
        //initialize
        dp[0] = true;

        //----------------一维dp------------------<------------这里还有化简余地 我们不需要把subStr子串单独拆分出来
        //我们可以直接在原来的str上拆分
        for (int i = 1; i <= length; i++) {
            String subStr = str.substring(0, i);
            //枚举 找到能够组成的条件 设置为true 不再需要继续往下找
            //dp[]判断是很快的 子集的组合长度循环到后面一定是长于子串的长度（子串也就是在字典中的单词）（当前dp=子集dp+子串）
            //因此我们从子集范围大到范围小进行枚举 加快速度
            for (int k = 0; k <= i; k++) {
                //subString和contain是耗时的 因此需要把dp[]判断放在前面
                //数据集中 字典的单词都是多字符的 因此 枚举值我们从subString大的范围往小的范围枚举
                //这样很容易提前找到true而跳出当前枚举循环
                if (dp[k] && wordSet.contains(subStr.substring(k, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        StringBuilder str = new StringBuilder(s);
        int length = str.length();

        boolean[] dp = new boolean[length + 1];
        //initialize
        dp[0] = true;

        for (int i = 1; i <= length; i++) {
            //String subStr = str.substring(0, i);
            for (int k = 1; k <= i; k++) {
                //直接从原来的str中截取子串
                //k=i dp[0]为true 如果子串在字典中存在 那么dp[i]=dp[0]&&str(i)=true
                if (dp[i - k] && wordSet.contains(str.substring(i - k, i))) {
                    dp[i] = true;
                    //<-------------------------!!!!!找到true一定要break！！！！！！！！！
                    break;
                }
            }
        }
        return dp[length];
    }
}

