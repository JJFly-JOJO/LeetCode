package StackPriorityQueue.NO215KthLargestElementinanArray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/3 20:54
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        //left=right 表示当前元素就是在正确的位置
        if (left < right) {
            int index = getIndex2(arr, left, right);
            quickSort(arr, left, index - 1);
            quickSort(arr, index + 1, right);
        }
    }

    /**
     * 找到当arr中某个元素的正确index
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int getIndex(int[] arr, int left, int right) {
        //为了防止出现最坏情况 比如顺序是从大到小 而要排序的结果是从小到大
        //如果我们每次都是从数组的第一个元素来找他到的正确位置 那么会出现O(n2)的最坏情况
        //因此我们每次从中间找
        int pivotIndex = left + (right - left) / 2;
        //为了方便 将pivot放在队首
        swap(arr, pivotIndex, left);
        int pivot = arr[left];
        //找到pivot的正确位置
        while (left < right) {
            //思考！！！！！！！！！这里当遇到元素等于pivot 那么用等号能否保证稳定性---> same pivot same
            //此思路最后需要讨论pivot所在位置 复杂
            /*while (left < right && arr[right] >= pivot) {
                right--;
            }
            //找到右侧不满足pivot的元素
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left == right) {
                break;
            }
            swap(arr, left++, right--);*/

            while (left < right && arr[right] >= pivot) {
                right--;
            }
            //找到右侧不满足大于等于的元素 放入left处 注意此时初始态的left是存放的暂时的pivot 是可以被替换的
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //找到左侧不满足小于等于的元素 放入right处 注意此时right处的值已经移动到了初始的left处
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;

    }

    /**
     * 另一个找到正确index的写法 注意我们把pivot换到另一个位置时 该位置不需要真正换 因为pivot是要最终
     * 确定的 减少交换次数
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int getIndex2(int[] arr, int left, int right) {
        int pivotIndex = left + (right - left) / 2;
        int pivot = arr[pivotIndex];
        swap(arr, left, pivotIndex);
        //j用来记录小于pivot的右边界 当i遍历时 遇到大于pivot的元素
        //j停下 i继续 一直走到又一个小于pivot的元素时 j++ 然后交换元素
        //此时j任然表示的是小于pivot元素的右边界
        int j = left;
        //遍历一次 把小于pivot的都移动到左侧
        for (int i = j + 1; i <= right; i++) {
            if (arr[i] < pivot) {
                j++;
                swap(arr, j, i);
            }
        }
        //将pivot与边界j交换位置
        swap(arr, j, left);
        return j;
    }

    private static void swap(int[] arr, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
