package Interview;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/26 14:21
 * @description
 */
public class ExchangeNode {

    public static void main(String[] args) {
        System.out.println(new ExchangeNode().GetMaxLength("({({((()(){()}})}{())})})[){{{([)()((()]]}])[{)]}{[}{)"));
    }

    public int GetMaxLength(String s) {
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        for (int i = 1; i < dp.length; i++) {
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                dp[i] = 0;
                continue;
            }
            if (chars[i] == ')' && chars[i - dp[i - 1] - 1] == '(') {
                int val = 0;
                if (i - dp[i - 1] - 2 >= 0) {
                    val = dp[i - dp[i - 1] - 2];
                }
                dp[i] = dp[i - 1] + 2 + val;
            } else if (chars[i] == ']' && chars[i - dp[i - 1] - 1] == '[') {
                int val = 0;
                if (i - dp[i - 1] - 2 >= 0) {
                    val = dp[i - dp[i - 1] - 2];
                }
                dp[i] = dp[i - 1] + 2 + val;
            } else if (chars[i] == '}' && chars[i - dp[i - 1] - 1] == '{') {
                int val = 0;
                if (i - dp[i - 1] - 2 >= 0) {
                    val = dp[i - dp[i - 1] - 2];
                }
                dp[i] = dp[i - 1] + 2 + val;
            } else {
                dp[i] = 0;
            }
        }
        int result = 0;
        for (int i = 0; i < dp.length; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;

    }


    /*public void swapListNode(Node head,int v1,int v2){
        Node l1=head;
        Node l2=head;
        Node first=new Node(0);
        first.next=head;
        Node pre1=first;
        Node pre2=first;
        int n1=0;
        int n2=0;
        while(l1!=null&&l1.val!=v1){
            l1=l1.next;
            pre1=pre1.next;
            n1++;
        }
        while(l2!=null&&l2.val!=v2){
            l2=l2.next;
            pre2=pre2.next;
            n2++;
        }
        if(l1==null||l2==null){
            return;
        }
        pre1.next=l2;
        pre2.next=l1;
        Node t=l2.next;
        l2.next=l1.next;
        l1.next=t;
        return;
    }*/

}

class Node {


}