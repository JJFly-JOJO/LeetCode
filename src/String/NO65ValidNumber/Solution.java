package String.NO65ValidNumber;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/18 13:51
 */
public class Solution {

    public static void main(String[] args) {
        //String str = "123e55e55";
        /*for (String temp : str.split("e")) {
            System.out.println(temp);
        }*/
        //System.out.println(str);
        //System.out.println(str.replace("\\s+", ""));
        //System.out.println(str.replace(" ", ""));
        System.out.println(new Solution().isNumber(".1"));
    }

    public boolean isNumber(String s) {
        //s.replace(" ", "");
        //先去除首尾的空格 并且判断第一个数是否是+ -
        s = s.trim();
        char[] chars = s.toCharArray();
        int length = s.length();
        if (length == 0) {
            return false;
        }
        boolean firstSymbol = chars[0] == '+' || chars[0] == '-';
        int startIndex = 0;
        if (firstSymbol) {
            if (length == 1) {
                return false;
            } else {
                startIndex = 1;
            }
        }
        boolean meetPoint = false;
        for (int i = startIndex; i < length; i++) {
            if (isNum(chars[i])) {
                continue;
            } else if (chars[i] == ' ') {
                //遇见数字或者正负符号（这两个是开头）后不能再有空格
                //由于最开始我们trim处理过 因此中间数字不能有空格
                return false;
            } else if (chars[i] == '+' || chars[i] == '-') {
                return false;
            } else if (chars[i] == 'e') {
                //以e作为分隔符 另一段调用另一个函数判断
                //e之前必须要有数字 并且当前e下标不能是最后一个
                if (i == startIndex || (!isNum(chars[i - 1]) && chars[i - 1] != '.') || i == length - 1) {
                    return false;
                } else {
                    return isNumberAfterE(chars, i + 1, length);
                }
            } else if (chars[i] == '.') {
                if (meetPoint || (i == startIndex && i == length - 1)
                        || (i == startIndex && !isNum(chars[i + 1]))
                        || (i == length - 1 && !isNum(chars[i - 1]))
                        || (i > startIndex && i < length - 1 && !isNum(chars[i - 1]) && !isNum(chars[i + 1]))) {
                    //小数点前后都没有数字
                    return false;
                } else {
                    meetPoint = true;
                }
            } else {
                //其他字符
                return false;
            }
        }
        return true;
    }

    private boolean isNumberAfterE(char[] chars, int startIndex, int length) {
        boolean firstSymbol = chars[startIndex] == '+' || chars[startIndex] == '-';
        int begin = startIndex;
        if (firstSymbol) {
            if (startIndex == length - 1) {
                return false;
            } else {
                begin = startIndex + 1;
            }
        }
        boolean meetPoint = false;
        for (int i = begin; i < length; i++) {
            if (isNum(chars[i])) {
                continue;
            } /*else if (chars[i] == ' ') {
                return false;
            } else if (chars[i] == '+' || chars[i] == '-') {
                return false;
            } else if (chars[i] == 'e') {
                return false;
            } else if (chars[i] == '.') {
                return false;
            } */ else {
                return false;
            }
        }
        return true;
    }

    private boolean isNum(char s) {
        return s >= '0' && s <= '9';
    }

}
