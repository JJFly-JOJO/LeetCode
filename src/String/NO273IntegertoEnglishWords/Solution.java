package String.NO273IntegertoEnglishWords;

/**
 * -----------新颖之处-------双数组组成一个map 数组下标作为中间key值 互相指向对应的num和string
 *                            而不是直接num指向string 这样我们可以很高效的模仿一个LinkedHashMap
 *
 * @author zzj
 * @version 1.0
 * @date 2020/9/17 13:49
 */
public class Solution {

    private int[] indexToNumber = new int[]{
            1000000000, 1000000, 1000, 100, 90, 80, 70, 60, 50, 40, 30, 20,
            19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0
    };

    /**
     * 自带一个空格 这样拼接字符串时就不用再考虑空格问题 只用在最后结果删除最后一个空格
     */
    private String[] indexToString = new String[]{
            "Billion ", "Million ", "Thousand ", "Hundred ", "Ninety ", "Eighty ", "Seventy ", "Sixty ", "Fifty ", "Forty ", "Thirty ", "Twenty ",
            "Nineteen ", "Eighteen ", "Seventeen ", "Sixteen ", "Fifteen ", "Fourteen ", "Thirteen ", "Twelve ", "Eleven ", "Ten ", "Nine ", "Eight ",
            "Seven ", "Six ", "Five ", "Four ", "Three ", "Two ", "One ", "Zero "
    };


    public static void main(String[] args) {
        //System.out.println(Integer.MAX_VALUE);
        System.out.println(new Solution().numberToWords(12345));
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (num > 0) {
            int count = num / indexToNumber[i];
            if (count > 0) {
                num -= count * indexToNumber[i];
                if (i <= 3) {
                    res.append(translate(count)).append(indexToString[i++]);
                } else {
                    res.append(indexToString[i++]);
                }
            } else {
                i++;
            }
        }
        //减去最后一个空格
        return res.deleteCharAt(res.length() - 1).toString();
    }

    /**
     * 传入的num最大为999 最小为1
     *
     * @param num 传入的num一定大于0
     * @return
     */
    private String translate(int num) {
        StringBuilder res = new StringBuilder();
        //从100开始
        int i = 3;
        while (num > 0) {
            int count = num / indexToNumber[i];
            //此函数传入的num值只有999-1 count>1只可能出现在100时
            //注意只有100以上的数才有前缀（count不为1时 100以下 不会出现count大于1情况）
            if (count > 0) {
                num -= count * indexToNumber[i];
                if (i <= 3) {
                    res.append(translate(count)).append(indexToString[i++]);
                } else {
                    res.append(indexToString[i++]);
                }
            } else {
                i++;
            }
        }
        return res.toString();
    }

}
