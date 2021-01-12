package Math.NO67AddBinary;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().addBinary("1110110101",
                "1110111011"));
    }

    /**
     * 先二进制转十进制相加 再将十进制转换为二进制
     * 此方法存在问题：
     * 存在整型溢出的问题
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinaryForErro(String a, String b) {
        //int numA=GetBinary(a);
        //int numB=GetBinary(b);
        int sum = GetBinary(a) + GetBinary(b);
        if (sum == 0) {
            return "0";
        }
        int size = 0;
        int[] sumBinary = new int[32];
        while (sum > 0) {
            int twoNums = 0;
            int cur = 1;
            //int curSum;
            while (cur * 2 <= sum) {
                cur *= 2;
                twoNums++;
            }
            sumBinary[size++] = twoNums;
            sum -= cur;
        }
        int res = 0;
        for (int i = 0; i < size; i++) {
            //这一步存在整型溢出的问题！！！！！！
            res += Math.pow(10, sumBinary[i]);
        }
        return String.valueOf(res);
        /*sumBinary[size++] = 1;
        int remiander = sum % 2;
        sum /= 2;
        while (sum != 0) {
            for (int i = 0; i < size; i++) {
                sumBinary[i] *= 10;
            }
            if (remiander == 1) {
                sumBinary[size++] = 1;
            }
            remiander = sum % 2;
            sum = sum / 2;
        }
        *//*if (remiander == 1) {
            sumBinary[size++] = 1;
        }*//*
        int res = 0;
        for (int i = 0; i < size; i++) {
            res += sumBinary[i];
        }
        return String.valueOf(res);*/
    }

    public int GetBinary(String a) {
        int length = a.length();
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum = (int) (sum + (a.charAt(i) - '0') * Math.pow(2, length - i - 1));
        }
        return sum;
    }

    /**
     * 按位运算 用一个变量保存进位数
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        //进位标志
        int cf = 0;
        //注意语法
        //int m=1,int n=2;//错误
        //int m=1,n=2;//正确
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int curSum = 0;
            curSum += i >= 0 ? a.charAt(i) - '0' : 0;
            curSum += j >= 0 ? b.charAt(j) - '0' : 0;
            curSum += cf;
            //直接将当前位的结果append 这样得到的最终结果表示的顺序是相反的 需要reverse
            res.append(curSum % 2);
            cf = curSum / 2;
        }
        if (cf == 1) {
            res.append("1");
        }
        return res.reverse().toString();
    }

}
