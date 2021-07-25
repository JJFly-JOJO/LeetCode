package Interview;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/7 22:12
 * @description
 */
public class MinStep {

    public static void main(String[] args) {
        System.out.println(new MinStep().getMinStep(555, 666));
    }

    public int getMinStep(int x, int y) {
        if (y <= x) {
            return x - y;
        }
        int step = 0;
        while (y > x * 2) {
            //让y减半而不是x加倍？？？---------让x乘2正好变到y y/2越小 x变到y/2步骤越少 
            if ((y & 1) == 1) {
                y++;
                step++;
            }
            y /= 2;
            step++;
        }
        if ((y & 1) == 1) {
            //odd
            int v = (y + 1) / 2;
            return (x - v) + 2 + step;
        }
        int v = y / 2;
        return (x - v) + 1 + step;
    }

}
