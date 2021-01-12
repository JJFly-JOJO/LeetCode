package WeekContest.SingleWeek.NO209;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/5 10:47
 * Java提供了Collections.sort()来对数组进行排序，其内部采取的是归并排序；此外还提供了Arrays.sort()，Arrays.sort()采用了2种排序算法
 * – 基本类型数据使用快速排序法，对象数组使用归并排序。
 *        那么在实际中采用哪一种比较好呢？这个问题要分排序的对象是基本数据类型数组还是对象数组来看。
 *        1. 排序基本类型的数组。此时还要看数据规模，不同数据规模下算法的执行效率不一样，
 *        具体数据规模参考这篇文章：java-归并排序与快排的效率比较（建议多找些其他文章看看，这篇文章的结果仅供参考，最好自己动手试试）。
 *        2. 排序对象数组。采用Collections.sort()。当然也可能有其他更好的方法或者自己实现，
 *        具体情况具体分析。这里主要考虑时间效率，实际中还有其他很多情况要考虑。
 */
public class TestQuickSortTreeSetTimSort {
    public static void main(String[] args) {
        TreeSet set = new TreeSet<>();
        int[] arr = getArr();
        int[] arr2 = arr.clone();
        ArrayList arrayList = new ArrayList(Arrays.asList(arr2));
        long start = System.nanoTime();
        for (int val : arr) {
            set.add(val);
        }
        long end = System.nanoTime();
        System.out.println(end - start);

        start = System.nanoTime();
        Arrays.sort(arr2);
        end = System.nanoTime();
        System.out.println(end - start);

        start = System.nanoTime();
        Collections.sort(arrayList);
        end = System.nanoTime();
        System.out.println(end - start);


    }

    public static int[] getArr() {
        int arr[] = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(arr.length) + 1;
        }
        return arr;
    }
}

