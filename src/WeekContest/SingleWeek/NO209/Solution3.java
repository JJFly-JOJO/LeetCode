package WeekContest.SingleWeek.NO209;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/4 11:14
 * [[13779926,599856510],[195766825,597976710],
 * [119515491,575316056],[744777345,796161766],
 * [187192636,870346582],[413112378,430889309],[436399518,387904921],[296153131,221188617],[536914240,985130562],[226391292,83241861]]
 * 64 [451961560,358354259]
 */
public class Solution3 {

    //private final double PI = 180 / 3.1415926;

    public static void main(String[] args) {
        List<Integer> p1 = new ArrayList<>();
        p1.add(1);
        p1.add(1);
        List<Integer> p2 = new ArrayList<>();
        p2.add(2);
        p2.add(2);
        List<Integer> p3 = new ArrayList<>();
        p3.add(3);
        p3.add(3);
        List<Integer> p4 = new ArrayList<>();
        p4.add(4);
        p4.add(4);
        System.out.println(new Solution3().getAngle(p1, p2));
        System.out.println(new Solution3().getAngle(p1, p3));
        System.out.println(new Solution3().getAngle(p1, p4));
        List<List<Integer>> points = new ArrayList<List<Integer>>() {
            {
                add(p2);
                add(p3);
                add(p4);
            }
        };
        System.out.println(new Solution3().visiblePoints(points, 0, p1));
    }

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        double angleD = (double) angle + 0.0000001;
        int res = 0;
        int length = points.size();
        if (length == 1) {
            return 1;
        }
        List<Double> angles = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            double tempA = getAngle(location, points.get(i));
            if (tempA == 400) {
                res++;
            } else {
                angles.add(tempA);
            }
        }
        int angleLen = angles.size();
        if (angleLen == 1) {
            return res + 1;
        }
        Collections.sort(angles);
        int last = angleLen - 1;
        double[] differ = new double[angleLen];
        for (int i = 0; i < last; i++) {
            differ[i] = angles.get(i + 1) - angles.get(i);
        }
        differ[last] = 360 - angles.get(last) + angles.get(0);
        //利用双指针
        int slow = -1;
        int fast = 0;
        double sumAngle = 0;
        int tempRes = 1;
        int addRes = 1;
        while (true) {
            sumAngle += differ[fast];
            if (sumAngle <= angleD) {
                fast++;
                tempRes++;
                //到头 从0开始
                if (fast == angleLen) {
                    fast = 0;
                }
                //slow还未走
                if (fast == 0) {
                    tempRes--;
                    break;
                }
                if (fast == slow) {
                    break;
                }
            } else {
                if (tempRes > addRes) {
                    addRes = tempRes;
                }
                while (sumAngle > angleD) {
                    slow++;
                    if (slow == angleLen) {
                        break;
                    }
                    sumAngle -= differ[slow];
                    tempRes--;
                }
                sumAngle -= differ[fast];
            }
        }
        if (tempRes > addRes) {
            addRes = tempRes;
        }
        return res + addRes;
    }

    /**
     * 注意这里也可以用tan来计算角度
     * @param location
     * @param point
     * @return
     */
    private double getAngle(List<Integer> location, List<Integer> point) {
        double x = point.get(0) - location.get(0);
        double y = point.get(1) - location.get(1);
        double len = Math.sqrt(x * x + y * y);
        if (len == 0) {
            return 400;
        }
        if (y == 0) {
            return x > 0 ? 0 : 180;
        }
        if (x == 0) {
            return y > 0 ? 90 : 270;
        }
        double xV = x / len;
        double angle = Math.toDegrees(Math.acos(xV));
        if (y > 0) {
            return angle;
        } else {
            return 360 - angle;
        }
    }

    public int visiblePoints2(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int inPos = 0;//记录与站位点相同的观察点
        //计算所有点与站位点的角度
        //------------------------------用tan计算角度---------------------------//
        /*for(List<Integer> point:points){
            if(point.get(1)==location.get(1)){
                if(point.get(0)==location.get(0)){
                    inPos++; continue;
                }
                angles.add(point.get(0)>location.get(0) ? 0D : 180D);
            }
            else if(point.get(0)==location.get(0))
                angles.add(point.get(1)>location.get(1) ? 90D : 270D);
            else {
                double tan = (point.get(1)-location.get(1))/(double)(point.get(0)-location.get(0));
                double ang = Math.toDegrees(Math.atan(tan));
                if(point.get(0)>location.get(0) && point.get(1)>location.get(1))
                    angles.add(ang);
                else if(point.get(0)>location.get(0) && point.get(1)<location.get(1))
                    angles.add(ang+360D);
                else angles.add(ang+180D);

            }
        }*/
        int length = points.size();
        for (int i = 0; i < length; i++) {
            double tempA = getAngle(location, points.get(i));
            if (tempA == 400) {
                inPos++;
            } else {
                angles.add(tempA);
            }
        }
        //排序
        angles.sort(Comparator.naturalOrder());
        int L = 0, wsize = 0, max = 0, S = angles.size();
        //窗口大小wsize, 窗口范围 [L, i]
        OUTER:
        //技巧！！！！--------(i + 1) % length解决滑动窗口（快指针）走到最后一个元素又需要重头走的情况
        for (int i = 0; i < S; i = (i + 1) % S) {
            //当窗口右边界来到i时，删除左边已经观察不到的点
            //加360的原因 如果快指针i由走到了起始位置 此时与慢指针之差为负数 永远小于angle
            // 实质上是快指针已经多走了一圈 因此要加360
            while ((angles.get(i) - angles.get(L) + 360D) % 360D > angle) {
                L++;
                //慢指针（左边界）遍历了一次就停止遍历了
                if (L == S) break OUTER;
                wsize--;
            }
            wsize++;
            //达到最大可能 也直接返回
            if (wsize >= S) return wsize + inPos;
            max = Math.max(max, wsize);
        }
        return max + inPos;
    }

}