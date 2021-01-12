package Array.NO220ContainDuplicateIII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/9 15:22
 * <p>
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，
 * 使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，
 * 且满足 i 和 j 的差的绝对值也小于等于 ķ 。
 */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 整体思路：以k（索引范围）作为滑动窗口大小，在滑动窗口中寻找值，注意这里我们只需要单向滑动窗口即可
 * 因为在遍历的时候，左侧肯定已经遍历过了
 * <p>
 * !!!!HashMap put时---------->Long与Integer的hashcode相同--------->hash值冲突，调用equal判断-------->两个key的类型不同 形成链表！！！
 * get时---------->hashcode找到对应数组下标--------->Long的key与第一个Integr key进行equal判断 类型不同自然不等------->转到链表下一个节点
 * <p>
 * ！！！！！TreeSet的ceiling与floor---->一个是获取比当前元素大的最小元素 一个是获取比当前元素小的最大元素
 * <p>
 * long+int----->long
 * 如果数值类型不用L标识为long类型 那么jvm还是会以int类型对待 会出现精度损失的情况，例如：
 * long a1 = 10+Inetger.MAX_VALUE; 右侧相加任然是使用的int长度相加 再赋值给long 因此出现精度损失
 *
 * </>
 */
public class Solution {

    public static void main(String[] args) {
        /*int[] nums = new int[]{1, 5, 9, 1, 5, 9};
        System.out.println(new Solution().containsNearbyAlmostDuplicate(nums, 2, 3));
        System.out.println(2 / 4);
        System.out.println(6 / 4);
        System.out.println(-2 / 4);
        System.out.println(-6 / 4);*/
        //HashMap中 key值的Long与Value的区别！！！！！！！！！！！！
        /*Map<Object, String> map = new HashMap<>();
        Integer b = 123;
        map.put(b, b + "int");
        System.out.println(b.hashCode() == c.hashCode());  //true
        System.out.println(map.get(c));   //null
        map.put(c, c + "long");  //  size =2
        System.out.println("key c=" + map.get(c));
        System.out.println("key b=" + map.get(b));*/

        //int 与 long
        int maxInt = Integer.MAX_VALUE;
        System.out.println("MaxInteger=" + Integer.MAX_VALUE);
        Long c = 123L;
        long m = maxInt + c;
        System.out.println(m);
        long l1 = Integer.MAX_VALUE+100;
        long l2 = 100;
        //l1 += l2;
        System.out.println(l1);

    }

    /**
     * 方法一：滑动窗口中的数据结构以平衡二叉树（TreeSet）组织，提高搜索、插入、删除（窗口元素溢出 删除最开始加入的元素）
     * 查找效率为logn
     * <p>
     * 对比：如果我们滑动窗口用的是一个有序数组而非平衡二叉树呢？
     * 我们理想的情况下，滑动窗口如果是一个有序数组，那么我们利用两次二分查找（logn）找当前元素x的x-t与x+t是否存在即可
     * 但是查找效率保证了logn，但是每次滑动窗口插入与删除元素的复杂度呢？ 对于数组，插入与删除的复杂度是线性的O（n）复杂度，
     * 可见，与平衡二叉树的logn的插入与删除完全不能相比
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums,
                                                 int k, int t) {
        TreeSet<Long> treeWindow = new TreeSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            //获取平衡二叉树中 大于当前nums[i]的最小的一个元素
            Long temp = treeWindow.ceiling((long) nums[i]);
            //!!!!!!!!!!!!!!易错点 整数溢出的情况<-----------[-2147483648,2147483647]
            //k=1
            //t=1
            //!!!!!!相减 如果被减数是负数是造成整数溢出的原因 因此我们把减移到右侧
            if (temp != null && /*temp - nums[i] <= t*/temp <= nums[i] + t) {
                return true;
            }
            //获取平衡二叉树中 小于当前nums[i]的最大的一个元素
            temp = treeWindow.floor((long) nums[i]);
            if (temp != null && /*nums[i] - temp <= t*/nums[i] <= temp + t) {
                return true;
            }
            //没有找到满足条件的值 当前元素插入二叉树中
            treeWindow.add((long) nums[i]);
            //判断滑动窗口值是否溢出 注意易错点！！！！每一次遍历的时候 当前nums[i]也是默认加入到滑动窗口中集合的
            // 不是调用treeWindows.add才算加入
            if (treeWindow.size() >= k + 1) {
                //溢出最开始加入的一个元素
                treeWindow.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 方法二：基于桶排序-----桶排序的思路：我们将具有相同属性的元素放在一个桶中，我们以不同的排序算法对桶中元素进行排序，
     * 外部桶也可以排好序（基于桶标签key值）这样整个集合就是有序的了
     * <p>
     * 桶的设计：将值属于0-t的nums[i]值放入一个桶中，该桶中的值就满足了两个值之差的绝对值小于等于t的要求，同时当前桶中的值可能与
     * -（t+1）~-1和t+1~2t+1中桶的值满足两个值之差小于等于t的要求 也要进行判断
     *
     * <p>
     * 针对此题的特点：桶中元素只要有两个 那么我们就找到结果了（也就是说Map集合contain为真 那么就返回true），我们没有必要用
     * list存下属于此标签的所有元素（不需要Map<Integer,List<Integer>>）
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicateForBucket(int[] nums,
                                                          int k, int t) {
        int length = nums.length;
        //int转long
        /*long[] numss = new long[length];
        for (int i = 0; i < length; i++) {
            numss[i] = (long) nums[i];
        }*/
        //桶
        Map<Integer, Integer> bucket = new HashMap<>();
        //被除数
        int w = t + 1;
        //任然是滑动窗口 只不过滑动窗口用桶来存放数据了（相比上一方法 用平衡二叉树作为滑动窗口）
        for (int i = 0; i < length; i++) {
            int id = getBucketID(nums[i], w);
            if (bucket.containsKey(id)) {
                return true;
            }
            //判断左右相邻两个桶中的元素是否满足t要求
            //桶中元素个数必为1 也就不存在选择桶中哪个元素来比较的情况 因为个数超过1 就返回true
            Integer temp;
            if ((temp = bucket.get(id - 1)) != null) {
                if ((long) nums[i] - temp <= t) {
                    return true;
                }
            }
            if ((temp = bucket.get(id + 1)) != null) {
                if (temp - (long) nums[i] <= t) {
                    return true;
                }
            }
            bucket.put(id, nums[i]);
            //判断滑动窗口元素个数是否溢出（超过k）
            if (bucket.size() >= k + 1) {
                bucket.remove(getBucketID(nums[i - k], w));
            }
        }
        return false;
    }

    /**
     * 获取桶的标签ID(KEY值)
     * 这里我们根据题意，按照差值t来设计桶：
     * 0-t的元素放入桶1中（包括0），t+1-2t+1（包括t+1）的元素放入桶2中.......
     * 同时还有负数情况！
     * -（t+1）~-1应该放入桶中
     * 一个易错点：正数中，两数相除得到的double转int类型 都是向下转：例如：6/4=1.5-->1
     * 负数中，两数相除得到的double转int类型 都是向上转：例如：-6/4=-1.5-->-1
     * 注意：如果t范围是4 那么，对于0-4的值应该放入一个桶中，也就是说（0-4）/一个值得到的ID应该是同一个数
     * 可以看出（0-4）/t+1--->（0-4）/5==0--->放入到便签为0的桶中
     * 关键：针对负数！！！
     * 如果t的范围是4 那么，对于-5~-1的值应该放入一个桶中，ID值必须要相同
     * 如果-5~-1除以w（t+1=5），那么得到的标签值-5与其他数不相同，我们将其都加1 得到的除数都为0 再将结果-1 那么就能保证标签不重复
     * <p>
     * 用表达式表示：0~t--->(0~t)/(t+1)=0--->保证结果都相同
     *
     * @param val
     * @param w   被除数 （传入的值已经是t+1了）
     * @return
     */
    private int getBucketID(int val, int w) {
        return val >= 0 ? val / w : (val + 1) / w - 1;
    }

}
