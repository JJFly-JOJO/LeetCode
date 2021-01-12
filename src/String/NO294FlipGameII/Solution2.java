package String.NO294FlipGameII;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/7 17:03
 * @description ------------------------带有记忆的回溯 经典递归思想 不去追究递归下的情况 我们只根据当前函数意义来分析---------
 */
public class Solution2 {

    Map<String, Boolean> playerCanWin = new HashMap<>();

    public boolean canWin(String s) {
        if (playerCanWin.containsKey(s)) {
            return playerCanWin.get(s);
        }
        int len = s.length();
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                String ss = s.substring(0, i - 1) + "--" + s.substring(i + 1, len);
                //我们根本不需要关系下一层递归的情况 就根据函数意义 在当前该玩家翻牌后，另一个玩家翻牌
                // （调用canWin）如果返回false 就说明当前一定赢 记忆 不能赢 那么将当前情况也记录为不能赢
                //然后在该层继续遍历
                if (!canWin(ss)) {
                    playerCanWin.put(s, true);
                    return true;
                }
                //不能赢 也要记忆
                playerCanWin.put(s, false);
            }
        }
        return false;
    }
}
