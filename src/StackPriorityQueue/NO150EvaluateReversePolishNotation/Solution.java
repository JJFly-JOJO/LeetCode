package StackPriorityQueue.NO150EvaluateReversePolishNotation;

public class Solution {

    public static void main(String[] args) {
        int test = 13 / 5;
        System.out.println(test);
        String[] tokens = new String[]{"4", "13", "5", "/", "+"};
        System.out.println(new Solution().evalRPN(tokens));
    }

    /**
     * 逆波兰表达式：一种后缀表达式 运算符写在后
     * 优势：去掉括号后表达式无歧义
     * 总体思路:用栈实现
     * 思考：直接用stack实现还是可以用数组来代替栈实现?加快执行速度
     * <p>
     * 使用数组代替栈实现：只需要一个size下标辅助记录当前尾部index即可
     * 数组的大小：由于一个运算符号左右必须有两个数 因此数的总数总是运算符号加1
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        //用数组模拟栈
        int[] arrayLikeStack = new int[tokens.length / 2 + 1];
        //用size记录top下标
        int size = -1;

        for (String temp :
                tokens) {
            switch (temp) {
                case "+": {
                    int tp1 = arrayLikeStack[size--];
                    int tp2 = arrayLikeStack[size--];
                    arrayLikeStack[++size] = tp1 + tp2;
                    break;
                }
                case "-": {
                    //注意减法是有先后顺序的！！！
                    int tp1 = arrayLikeStack[size--];
                    int tp2 = arrayLikeStack[size--];
                    arrayLikeStack[++size] = tp2 - tp1;
                    break;
                }
                case "*": {
                    int tp1 = arrayLikeStack[size--];
                    int tp2 = arrayLikeStack[size--];
                    arrayLikeStack[++size] = tp1 * tp2;
                    break;
                }
                case "/": {
                    //注意除法是有先后顺序的！！！
                    int tp1 = arrayLikeStack[size--];
                    int tp2 = arrayLikeStack[size--];
                    arrayLikeStack[++size] = tp2 / tp1;
                    break;
                }
                default: {
                    arrayLikeStack[++size] = Integer.valueOf(temp);
                }
            }

        }
        return arrayLikeStack[size];
    }

}
