package Array.NO42TrappingRainWater;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/13 17:12
 * 题目描述：
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class Solution {

    public static void main(String[] args) {
        int[] height = new int[]{4, 2, 3};
        //int[] height = new int[]{5, 4, 1, 2};
        System.out.println(new Solution().trap(height));
    }

    public int trapErro(int[] height) {
        int length = height.length;
        int leftIndex = 0;
        int rightIndex = 0;
        int res = 0;
        //left先走 先找到第一个左边界
        while (leftIndex < length - 1) {
            if (height[leftIndex] > height[leftIndex + 1]) {
                break;
            }
            leftIndex++;
        }
        //队列存放从左边界开始的下降点的高度
        Queue<Integer> queue = new LinkedList<>();
        lable:
        while (leftIndex < length - 1) {
            //左边界高度值加入队列中
            //queue.add(height[leftIndex]);
            //左边界找到 开始走right
            rightIndex = leftIndex + 1;
            int tempRes = 0;
            //标记右边界的第一个上升点
            int rightRisePoint = 0;
            while (rightIndex < length - 1) {
                tempRes += (height[leftIndex] - height[rightIndex]);
                //记录下降点高度
                queue.add(height[rightIndex]);
                //找right的上升点 right初始首先能够保证小于leftIndex
                if (height[rightIndex] < height[rightIndex + 1]) {
                    rightRisePoint = rightIndex + 1;
                    //找到右的第一个上升点 找第一个下降点
                    while (rightRisePoint < length - 1) {
                        int vval = height[leftIndex] - height[rightRisePoint];
                        if (vval > 0) {
                            tempRes += vval;
                        }
                        //找到第一个下降点 或者到达边界
                        if (height[rightRisePoint] > height[rightRisePoint + 1]) {
                            break;
                        }
                        rightRisePoint++;
                    }
                    //注意 如果到达的是length-1边界 此时的vval还没有计算
                    if (rightRisePoint == length - 1) {
                        int vval = height[leftIndex] - height[rightRisePoint];
                        if (vval > 0) {
                            tempRes += vval;
                        }
                    }
                    //当前rightRise为右边界 减差值
                    if (height[rightRisePoint] - height[leftIndex] < 0) {
                        int lenght = rightRisePoint - leftIndex;
                        int val = height[leftIndex];
                        while (queue.peek() > height[rightRisePoint]) {
                            int temp = queue.poll();
                            tempRes -= (val - temp) * lenght--;
                            val = temp;
                        }
                        //左侧高于右边界峰值减完后 还需要减一次右侧峰值
                        tempRes -= (val - height[rightRisePoint]) * lenght;
                    }
                    res += tempRes;
                    queue.clear();
                    leftIndex = rightRisePoint;
                    continue lable;
                } else {
                    rightIndex++;
                }
            }
            //如果右边界一直没有找到上升点 那么此处将最终length-1下标赋给left 防止死循环
            leftIndex = rightIndex;
        }
        return res;
    }

    /**
     * 区间融合
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int length = height.length;
        if (length == 0) {
            return 0;
        }
        int left = 0;
        //initialize
        while (left < length - 1 && height[left] <= height[left + 1]) {
            left++;
        }
        //找到初始第一个左边界值
        int maxLeft = height[left];
        //Pair 第一个值存放左高度值 第二个值存放对应下标
        Stack<Pair<Integer, Integer>> stack;
        stack = new Stack<>();
        stack.push(new Pair<>(height[left], left));
        int index = left;
        while (index < length) {
            //递减区间
            while (index < length - 1 && height[index] >= height[index + 1]) {
                index++;
            }
            //排除没有找到区间的情况 index已经到了length-1处
            if (index == length - 1) {
                break;
            }
            //递增区间
            while (index < length - 1 && height[index] <= height[index + 1]) {
                index++;
            }
            //合并区间 合并最多合并到最大左值处
            while (!stack.isEmpty() && stack.peek().getKey() != maxLeft && stack.peek().getKey() <= height[index]) {
                stack.pop();
            }
            //更新最大左值maxLeft
            if (height[index] > maxLeft) {
                maxLeft = height[index];
            }
            //当前index入栈
            stack.push(new Pair<>(height[index], index));
        }
        //求取每个区间值
        int res = 0;
        while (!stack.isEmpty()) {
            Pair<Integer, Integer> rightBoudary = stack.pop();
            if (!stack.isEmpty()) {
                Pair<Integer, Integer> leftBoundary = stack.peek();
                int val = Math.min(leftBoundary.getKey(), rightBoudary.getKey());
                for (int i = leftBoundary.getValue() + 1; i < rightBoudary.getValue(); i++) {
                    int temp = val - height[i];
                    if (temp > 0) {
                        res += temp;
                    }
                }
            }
        }
        return res;
    }

}
