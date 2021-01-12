package BinarySearch.NO278FirstBadVersion;

public class Solution extends VersionControl {

    public static void main(String[] args) {
        System.out.println((int)1/2);
    }

    //注意审题 第一个版本有错后 之后的版本都有错
    //二分查找的内容一定要事先排好序
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;//注意这里没有用(left+right)/2是防止整数溢出<-----------------!!!
            if (isBadVersion(mid)) {
                right = mid;
            } else
                //中间索引 mid极端情况时left与right相邻
                left = mid + 1;//注意这里不能用left=mid 否则会出现死循环 无法满足left>=right退出循环的情况
            //不使用right=min-1原因：mid是向下取整！！！！！！！！！！！！！！！！！！！
        }
        return left;
    }
}
