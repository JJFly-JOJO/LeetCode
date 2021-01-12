package Random.NO398RandomPickIndex;

import java.util.Random;

/**
 * -------------------笔记 证明--------------
 * <p>
 * ！！！！！！！类比NO384
 * <p>
 * 蓄水池抽样问题 元素不断的进入集合 如何保证我最终抽取出来的这个元素的概率是最终集合总量n的n分之一
 * <p>
 * 对于上一个问题：我们是每一次抽取当前集合的m分支一 最终每个元素的抽取概率(第n轮抽取元素e的概率等于从总元素中抽取到元素e的概率（概率均匀分布）)
 * <p>
 * 对于蓄水池抽样问题 可以理解为是上个问题的正向操作（上一个问题是每一轮从集合取出一个元素 蓄水池问题是每一轮从集合中加入一个元素）
 * <p>
 * 如何证明如果之前轮次已经抽取中一个数据 而后加入元素后 最终集合数n分支一是等于之前轮抽中的那一个元素
 * <p>
 * *********与动态规划有些类似 我们关注的是当前轮元素是否选中 不选中的概率由前面一轮分割选中与否
 */
public class Solution2 {

    int[] nums;

    public Solution2(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        Random random = new Random();
        int index = 0;
        //记录当前所要找的target的元素个数
        //我们是在当前要找的目标target集合中取一个target 不是在所有元素中找一个target
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
                //以当前集合总数分支一的概率获取当前元素index
                //生成1/n的两种方法
                //方法一：random.nextInt() % (i + 1) nextInt()返回一个均匀分布的int值 这个值与当前集合总数取余 余值概率是相等的
                //方法二:random.nextInt(i + 1) 在当前集合i+1的综合中按均匀概率取出一个值
                if (random.nextInt() % count == 0/*1 2 3..小于i+1都可以*/) {
                    index = i;
                }
            }
        }
        return index;
    }
}
