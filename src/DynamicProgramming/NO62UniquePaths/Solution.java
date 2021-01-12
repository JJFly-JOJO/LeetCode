package DynamicProgramming.NO62UniquePaths;

import java.util.Arrays;

public class Solution {

    //动态规划 关键之一 找到初始值0或1下标对应的值
    //考虑优化空间的问题 mxn矩阵不会完全用上
    //注意有一个关键点：边界上（也就是m=0或者n=0）的时候 只有可能有一种走法
    //空间复杂度为O(2n)
    public int uniquePaths(int m,int n){
        int[] cur=new int[n];
        int[] pre=new int[n];//按行解决
        Arrays.fill(cur,1);//特殊情况 m=1 只有一行 则返回的是cur[n-1]
        //cur[0]=1;
        Arrays.fill(pre,1);
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                cur[j]=cur[j-1]+pre[j];
            }
            pre=cur.clone();
        }
        return cur[n-1];
    }

    //空间复杂度O(n)
    //注意到更新顺序 当前值等于左边值加上上方值 左边值是上一次更新的值 上方值是当前正要被改变的旧值
    public int uniquePathsForSolution2(int m,int n){
        int[] cur=new int[n];
        //int[] pre=new int[n];//按行解决
        Arrays.fill(cur,1);//特殊情况 m=1 只有一行 则返回的是cur[n-1]
        //cur[0]=1;
        //Arrays.fill(pre,1);
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                cur[j]=cur[j-1]+cur[j];//
            }
            //pre=cur.clone();
        }
        return cur[n-1];
    }

}
