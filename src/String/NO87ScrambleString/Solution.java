package String.NO87ScrambleString;

/**
 * ------笔记-----
 * -------------------区间DP问题-----------------
 * <p>
 * 动态规划是递归求解的正向for循环
 * <p>
 * 区间DP问题 关键点 1.问题如何分解成子问题 状态是什么 dp[][][]...的维度
 * 2.每一层的迭代 如何更新状态dp 也就是状态转义方程如何定义
 * 3.边界（区间） 子问题的区间长度len 状态dp的遍历范围 i j
 * 4.最终结果是要哪一个状态
 * 5.一定要定义好初始状态 也就是子问题规模为1时初值 初值的初始化一般无法在迭代中进行 需要单独拿出来赋值
 */
public class Solution {

    /**
     * 对于本题：
     * 问题二分化成两个子问题 而比较当前字符串是否是扰乱字符串 就是比较两个字符串
     * 对应的两个子串是否相同 首先长度要保证相同 也就是说对于两个字符串 我们的分割
     * 点k1 k2 一定要保证s1和s2分割后的子串长度相同 那么对于s1 如果k在某处后分割成了
     * a1 a2 那么s2的分割方法就有两种（1）分割点与s1的k1相同 保证分割后b1等于a1 b2等于
     * a2 也可以以s2尾部为开始点 分割k2 保证a1与b2相同
     * <p>
     * 状态如何表示：
     * 宏观上看 我们是要比较两个字符是否满足扰乱字符串 也就是说d[i][j][m][n]表示整个问题 此状态中
     * d[i][j][][]与d[][][m][n]如果为true 就说明两个字符串是扰乱字符串 其子问题就是由
     * d[i][j][m][n]=d[i][k][m][k]+d[k+1][j][k+1][n]
     * 注意到两个字符串长度必须相等 也就是说 i-j=m-n=len
     * 那么这个状态本质上就可以变换成三个变量 s1字符串i的起点 s2字符串j的起点 len当前子问题的规模
     * <p>
     * ！！！！！！！本质！！！！！！
     * len是用来控制子问题规模 也就是说不管dp维度如何 本质就是length=len1+len2（两层）
     * 而len子问题枚举的所有情况就有维度 本问题就是二维的 i层要遍历 j层要遍历 才能把当前len
     * 规模的子问题全部求解完
     * 而单个子问题len的求解 又需要由lenlen1 lenlen2两个子子问题组合 通过len的子问题分割点k
     * 遍历len的长度获得
     * <p>
     * 状态转移方程：
     * 对于给定len规模的字符串 我们只需要遍历1到len-1的分割点（注意不能有元素为0的叶子节点 也就是说
     * 不能分割成0 len的两个子串）如果在遍历过程中子串满足a1b1和a2b2 或者 a1b2和a2b1的条件 那么状态就找到了
     * <p>
     * 思考：dp[][]状态的表达是唯一性的吗 会出现多种解的dp[i][j]表示相同（i j相同）吗？
     * 答案肯定时不会的 首先子问题如果规模不同 len1与len2长度不同 那么i与j表示的区间长度都不同
     * 肯定不会是数组中同一个元素 如果子问题规模相同时 如果i相同j相同 自然表示的是一个子问题 不同子问题的
     * i j起点终点都不同 自然也不会指向数组中的同一个元素
     * <p>
     * ！！！！！！！！！数组以hash表看待！！！！！！！
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int length;
        if ((length = ch1.length) != ch2.length) {
            return false;
        }
        //dp
        //length长度有0到length一共length+1
        boolean[][][] dp = new boolean[length][length][length + 1];
        //initialize
        //最小子问题规模为1 length为0的元素不会用上
        //要遍历完规模为len的所有子问题 要两层循环
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (ch1[i] == ch2[j]) {
                    dp[i][j][1] = true;
                }
                //dp[i][j][1] = ch1[i] == ch2[i];
            }
        }

        //区间dp
        //外层控制子问题规模 从规模2开始枚举
        for (int len = 2; len <= length; len++) {
            //(length-1)尾结点-len+1(+1 包括起始元素)
            for (int i = 0; i <= length - len; i++) {
                //s2的起点
                for (int j = 0; j <= length - len; j++) {
                    //子子问题（len规模）分割点k
                    for (int k = 1; k <= len - 1; k++) {
                        //状态转移方程 目的是要找到满足状态转移条件的值 为子问题len赋值
                        //k和len-k总是小于len 可以保证子问题永远在当前问题之前得到正确解
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        //另一种分割情况
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][length];
    }


    /**
     * 递归（回溯法）的思路 也就是dp动态规划的逆向思维！！！！！从总的问题开始向下分解递归到最小子问题
     * <p>
     * 而dp动态规划是从最小的子问题向上迭代到最终问题
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScrambleForBackTracing(String s1, String s2) {
        //---------------------------递归的返回条件-----------------//
        if (s1.length() != s2.length()) {
            return false;
        }
        //长度相等先判断字符串是否相同
        if (s1.equals(s2)) {
            return true;
        }

        //判断字符个数是否一致
        int n = s1.length();
        int[] map = new int[26];
        for (int i = 0; i < n; i++) {
            int index1 = s1.charAt(i) - 'a';
            int index2 = s2.charAt(i) - 'a';
            //s1 +1  s2 -1 如果对应字符个数都相等 最后数组元素都为0
            map[index1]++;
            map[index2]--;
        }
        for (int temp : map) {
            if (temp < 0) {
                return false;
            }
        }
        //------------------以上条件都满足 再向下分解成子问题递归 以上都是为了剪枝 减少递归层数-----------//
        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i, n), s2.substring(i, n))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(n - i, n))
                    && isScramble(s1.substring(i, n), s2.substring(0, n - i))) {
                return true;
            }
        }
        return false;
    }
}
