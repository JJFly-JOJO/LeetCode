package Math.NO43MultiplyStrings;

/**
 * -------------------笔记--------------
 */
public class Solution {

    private final String ZERO = "0";

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new Solution().multiplyForAdd("0", "36"));
        System.out.println(new Solution().multiply("112", "36"));
    }

    public String multiply(String num1, String num2) {
        int digit1 = num1.length();
        int digit2 = num2.length();
        if (num1.equals(ZERO) || num2.equals(ZERO)) {
            return "0";
        }
        //两数相乘 最大位数为M+N
        // 证明：例如 123 34
        //       我们以123为被乘数 34为乘数 则有： 100>34>=10 则不等式左右乘以123 最大可能增加两位 最小可能增加一位

        //注意res结果 高位对应index值小 低位对应index值大
        int[] res = new int[digit1 + digit2];
        //将num1 num2转换为整型字符串形式
        int[] num1ForInt = new int[digit1];
        char[] temp = num1.toCharArray();
        for (int i = 0; i < digit1; i++) {
            num1ForInt[i] = temp[i] - '0';
        }
        temp = num2.toCharArray();
        int[] num2ForInt = new int[digit2];
        for (int i = 0; i < digit2; i++) {
            num2ForInt[i] = temp[i] - '0';
        }

        //竖式计算
        //用num2的每一位乘以num1
        for (int i = digit2 - 1; i >= 0; i--) {
            for (int j = digit1 - 1; j >= 0; j--) {
                //int cur=num1ForInt[j]*num2ForInt[i];
                int sum = res[i + j + 1] + num1ForInt[j] * num2ForInt[i];
                //进位
                //int cf = sum / 10;
                //i+j位赋值
                //int count = 1;
                res[i + j + 1] = sum % 10;
                //!!!!!!!!!!!!!!!!!!!!!!!!这里完全不用处理进位 i+j位可以保留两位的数值 等到减减循环时 会走到i+j的位置
                //                          此时就能一并加上i+j位置上的两位数值 然后个位存入当前位 其他位数值一并存入
                //                          再上一位
                //!!!!!!!!!!!!!!!!!!!!!!
                //----------还可以改进---------
                //就连当前i+j+1位都不区分进位和个位了 直接储存多位值 循环结束后 再遍历向前进位
                res[i + j] += sum / 10;
                /*while (cf != 0) {
                    sum = res[i + j + 1 - count] + cf;
                    cf = sum / 10;
                    res[i + j + 1 - count++] = sum % 10;
                }*/
            }
        }
        //得到结果 如果首位为0 舍去 从下一位开始（位数范围在M+N到M+N-1之间）
        int i = 0;
        if (res[0] == 0) {
            i++;
        }
        StringBuilder resStr = new StringBuilder();
        for (; i < res.length; i++) {
            resStr.append(res[i]);
        }

        return resStr.toString();
    }

    /**
     * 方法二 被乘数每一位与乘数相乘 严格要求0-9单个位（区别上一题 前一位可以是多位数值）
     * 然后将每个结果按位相加 如果用数组存储每一次位与被乘数的结果 那么我们只能创建
     * M+N大小的数组（因为随着位的增加 后面的0的个数也需要补充）这样是非常浪费空间的
     * 但是我们如果用字符串按位处理 那么我们只能在乘法和加法的时候都需要保证严格的向前进位
     * 同时 对每个乘法结果进行相加时 尾部会有许多+0的无效操作
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiplyForAdd(String num1, String num2) {
        if (num1.equals(ZERO) || num2.equals(ZERO)) {
            return ZERO;
        }
        String res = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            StringBuilder temp = new StringBuilder();
            for (int m = 0; m < num2.length() - 1 - i; m++) {
                temp.append(ZERO);
            }
            int num = num2.charAt(i) - '0';
            for (int j = num1.length() - 1, cf = 0; j >= 0 || cf > 0; j--) {
                int curNum = j >= 0 ? (num1.charAt(j) - '0') * num + cf : cf;
                temp.append(curNum % 10);
                cf = curNum / 10;
            }
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    private String addStrings(String res, String temp) {
        StringBuilder addRes = new StringBuilder();
        int cf = 0;
        for (int i = res.length() - 1, j = temp.length() - 1; i >= 0 || j >= 0 || cf > 0; i--, j--) {
            int temp1 = i >= 0 ? res.charAt(i) - '0' : 0;
            int temp2 = j >= 0 ? temp.charAt(j) - '0' : 0;
            int product = temp1 + temp2 + cf;
            addRes.append(product % 10);
            cf = product / 10;
        }
        return addRes.reverse().toString();
    }

}
