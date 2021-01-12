package Matrix.NO378KthSmallestElementinaSortedMatrix;

/**
 * 思路一：归并排序-最小堆！！！！！！！！！！！！！！！！！
 * 思路二：二分查找+利用该矩阵性质 每一次二分时：找到当前mid时对应的小于等于mid的元素一共多少个
 * 二分查找的深入理解！即便元素字面值无序 但是如果该元素映射的某一属性值是有序可寻（满足单调性） 那么我们
 * 任然可以对此属性进行二分查找 找到我们需要的满足某个属性值（比如此题的k）的元素（当前元素值满足矩阵中元素小于等于
 * 此元素值的个数为k个）
 */
public class Solution {

    public static void main(String[] args) {
        //int[][] matrix = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(new Solution().kthSmallest(matrix, 5));
    }

    /**
     * 只找两行思路有问题 可能存在当前列 0 1 2 3...k的元素值情况 并不一定第k个小的元素在两行用
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallestErro(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (col == 1) {
            //单列情况 直接返回k索引下标
            return matrix[k - 1][0];
        }
        //预判第k值可能出现的位置
        int rowCount = k / row;
        int judge = k % row;
        int count = (rowCount - 1) * row;
        int resK = 0;
        if (judge != 0) {
            resK = matrix[rowCount][0];
        } else {
            resK = matrix[rowCount - 1][0];
        }
        if (rowCount == 0) {
            //如果第k在第一行的特殊情况
            rowCount = 1;
            count = 0;
        }
        //第k个值只会出现在当前行和下一行 可能会存在当前行是最后一行的情况 因此排除此特殊情况
        for (int i = rowCount - 1; i < rowCount + 1 && i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (++count <= k) {
                    if (resK < matrix[i][j]) {
                        //在没有到达第k个元素之前 我们都要取当前遍历到的最大元素
                        resK = matrix[i][j];
                    }
                } else {
                    //达到第k个元素后 我们要取之后遍历到的最小元素
                    if (resK > matrix[i][j]) {
                        resK = matrix[i][j];
                    }
                }
            }
        }
        return resK;
    }

    /**
     * 思考：如何满足的二分查找性质------！！
     * 二分查找对应的元素集合 一定要是有序集合 也就是说 某一种属性是有从小到大或者从大到小的排列顺序的！！！
     * 二分查找 注意这里的二分查找的元素值我们还不能直接获取到 需要利用矩阵特性计算得到当前mid对应的值
     * 然后再决定往哪边缩小区间
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int length = matrix.length;
        //可以证明：左上角元素是整个矩阵的最小值 右下角元素是整个矩阵的最大值
        int left = matrix[0][0];
        int right = matrix[length - 1][length - 1];
        //！！！！！！！！！！！！！！！二分查找满足的体现
        //我们的mid值在left 和 right之间 如果mid值越接近left 那么小于等于mid值的元素个数就越少
        //如果mid值越接近right 那么小于等于mid值的元素个数就越多
        //可以发现 元素的这个属性（小于当前元素值的元素个数）是满足单调递增（可以有相等值）那么我们就可以
        //利用元素的这个属性实现某个元素满足当前属性值为k的查找
        while (left < right) {
            int mid = left + (right - left) / 2;
            //获取当前mid元素对应的属性值（小于mid值的矩阵元素个数）
            int val = getElementVal(mid, matrix);
            if (val > k) {
                right = mid;
            } else if (val < k) {
                left = mid + 1;
            } else {
                //即使当前mid值对应小于等于他的个数val满足我们要找的第k个元素
                //但是这时候我们不能直接返回 因为该元素mid可能并不是矩阵中的元素值
                //利用向下取整的特点 这里mid赋值给right 在left与right只差1并且left得到的val也是k时
                // 下取整到left及为正确值（因为要保证小于等于中的等于 等于才说明当前二分找到的值存在于矩阵中）
                //此时及 right=mid（left） 然后退出循环 返回right
                right = mid;
            }
        }
        return right;
    }

    private int getElementVal(int mid, int[][] matrix) {
        int length = matrix.length;
        //从左下角开始
        //！！！！右下的一定比左上的值大！！！<-------前提
        //记录当前元素值对应的小于等于该元素值的个数
        int res = 0;
        //当前列的起始点 最初起始点是左下角 也就是[0][length-1]处
        int startRowOfCurCol = length - 1;
        for (int i = 0; i < length; i++) {
            int j = startRowOfCurCol;
            for (; j >= 0; j--) {
                if (matrix[j][i] <= mid) {
                    break;
                }
            }
            res += j + 1;
            startRowOfCurCol = j;
        }
        return res;
    }

}
