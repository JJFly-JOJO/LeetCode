package Array.NO274HIndex;

import java.util.Arrays;

/**
 * ---------计数排序 以及 作图辅助证明----------
 *
 *
 */
public class Solution {
    public static void main(String[] args) {
        int num = 0;
        num++;
        System.out.println(num);
        Integer count = 0;
        count++;
        System.out.println(count);
    }


    /**
     * 方法一：直接用sort API
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int length = citations.length;
        if (length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        int i = length - 1;
        for (; i >= 0; i--) {
            if (citations[i] < length - i) {
                return length - i - 1;
            }
        }
        return length;
    }

    /**
     * 计数排序（hash+排序） 对于一串整数（非负）数组 我们如果利用数组下标就是有序的条件 遍历一遍数组 将数组下标下的元素
     * 对应此元素在数组中出现的个数 那么得到的这个数组也就是一个排号序的数组
     * 注意！！！前提 数组的所有元素都没有超过这个数组的长度范围
     * <p>
     * 对于此题 我们想要从小到大记录当前索引数对应有多少文献数量
     * <p>
     * 思考：我们需要开辟最大索引数长度的数组吗？
     * 由题意：我们找到的H指数是要这H篇论文的每篇文献引用数量都<至少>大于等于H 如果我们文献的引用指数都已经超过了总共的文献数量呢？
     * 如果当篇文献的引用数量都超过了所有论文数量，我们知道H指数最大只能取到所有文献数量n，也就是说，如果当前论文引用数量
     * citations[i]>n了 那么我们将此篇论文的引用数量变成n也是不会影响H的结果的
     * <p>
     * 我们也可以通过斜率图看出！
     * 思考：H指数是唯一的吗？
     * 肯定是唯一的，通过H的定义，H篇论文的每篇引用至少大于等于H，H是取的最大值！
     * 也可以通过斜率图看出
     *
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        int length = citations.length;
        if (length == 0) {
            return 0;
        }
        //索引有0 1 2...超过数组大小的以length计算
        int[] countinngSortArray = new int[length + 1];
        for (int i = 0; i < length; i++) {
            countinngSortArray[Math.min(length, citations[i])]++;
        }
        int hIndex = 0;
        int curIndex = length;
        /*while (curIndex >= 0 && hIndex <= curIndex) {
            hIndex += countinngSortArray[curIndex--];
        }
        hIndex-=countinngSortArray[++curIndex];
        if(hIndex<curIndex){
            return curIndex;
        }*/
        //!!!!!!!!!!!!!!!!!!!!!!!!误区！！！！！！！！！！！
        //[1,5,5]------>不是从5直接跳到1 发现1不满足大于2（当前个数） 而返回1
        //实际上中间还要经过2 3 4三个零值  [1,2,3,4,5,5,]其中2 3 4索引对应的文献数为0  curIndex走到2就已经返回了
        while (curIndex >= 0) {
            hIndex += countinngSortArray[curIndex];
            if(hIndex>=curIndex){
                break;
            }
            curIndex--;
        }
        return curIndex;
    }

}
