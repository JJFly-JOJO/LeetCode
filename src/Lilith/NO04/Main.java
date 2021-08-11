package Lilith.NO04;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/12 12:49 上午
 * @description
 */
public class Main {

    public static void main(String[] args) {
        MessageDeque<Integer> mq = new MessageDeque<>(10);
        mq.addMsg(1);
        System.out.println(mq.saleMsg());
        mq.saleMsg();
        System.out.println("end");
    }

}
