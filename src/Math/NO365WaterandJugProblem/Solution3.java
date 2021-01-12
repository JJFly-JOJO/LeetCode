package Math.NO365WaterandJugProblem;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/19 10:35
 * <p>
 * 数学方法：
 * 裴蜀定理（或贝祖定理）得名于法国数学家艾蒂安·裴蜀，
 * 说明了对任何整数a、b和它们的最大公约数d，关于未知数x和y的线性不定方程（称为裴蜀等式）：
 * 若a,b是整数,且gcd(a,b)=d，那么对于任意的整数x,y,ax+by都一定是d的倍数，
 * 特别地，一定存在整数x,y，使ax+by=d成立。
 * <p>
 * 最大公约数找法：辗转相除法 a%b=c--->a=b b=c--->while(b(c)==0)
 * <p>
 * 我们分析该问题：首先需要弄清两个容器的状态-----当前当个容器中各自的水量及对应一个状态
 * ---我们可以再把状态定的更模糊一点 当前两个容器的总水量为一个状态 当总水量等于目标水量时 该状态及找到
 * 1.不可能存在两个容器都没有满的情况。（由题意 容器的水只能被倒满或者倒空）
 * 2.容器中有水但是没有满（由结论1 另一个容器只有可能是满或者空） 那么该容器水倒满是没有意义的，
 * 就相当于初始状态直接加满 同理 该容器水倒空也是没有意义的
 * 3.从容器x倒给容器y（或者y给x）容器中总水量没有变 因此相当于状态没有变
 * 4.由上面两个结论我们可以得到，每一次倒水 总水量的状态只有增加x 减少x 增加y 减少y四种状态转换
 * 由此我们可以得到式子：a代表x容器最终是减（还是增）了几次 b同理
 * ax+by=z
 * 5.a,b解释： 若a<0 b>0(或者a<0 b>0) 表示给y壶水倒满（对应b） y壶水倒给x壶 倒满后x壶再倒空（对应a）（x壶自己倒满再倒空是没有意义的）
 * 6.特判 如果x+y<z 自然是不可能倒出z升水的
 */
public class Solution3 {

    public boolean canMeasureWater(int x, int y, int z) {
        //特判
        if (x + y < z) {
            return false;
        }
        if (z == 0) {
            return true;
        }
        int gcb = getGCB(x, y);
        return z % gcb == 0;
    }

    /**
     * 获得两个整数的最大公约数 辗转相除法
     * 注意：0与任何非零数的最大公约数是这个非零数
     *
     * @param x
     * @param y
     * @return
     */
    private int getGCB(int x, int y) {
        if (x == 0) {
            return y;
        }
        if (y == 0) {
            return x;
        }
        do {
            int remainder = x % y;
            x = y;
            y = remainder;
        } while (y != 0);
        return x;
    }
}
