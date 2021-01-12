package LinkedList.NO160IntersectionofTwoLinkedLists;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/30 11:20
 */
public class Solution {


    /**
     * 首先要清楚如果存在相交节点 那么相交节点之后的AB链表段长度都是相同的 也就是说链表AB的比较是需要从
     * 某一个节点之后的AB链表长度都相同才有比较的意义
     *
     * 利用循环的思想 如果链表A的长度小于链表B的长度 那么两个链表同时向前进时
     * 链表A先走到头 这时候链表B与A相差lengthB-lengthA
     * 此时如果让链表A接上链表B 两个节点再同时前进 链表B走到头 再让此节点转到链表A
     * 那么就可以抵消掉两个链表AB之间的节点数量差
     *
     * -------进一步理解 相当于链表A接上链表B 链表B接上链表A 这样两个链表的长度就相等
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return dfs(headA, headB, headA, headB);
    }

    private ListNode dfs(ListNode headA, ListNode headB, ListNode headA1, ListNode headB1) {
        if (headA == headB) {
            return headA;
        }
        //headA与headB如果同时为null 那么在上一个分支就已经返回了
        if (headA == null) {
            return dfs(headB1, headB.next, headA1, headB1);
        } else if (headB == null) {
            return dfs(headA.next, headA1, headA1, headB1);
        } else {
            return dfs(headA.next, headB.next, headA1, headB1);
        }
    }

    /*private void dfs(ListNode headA, ListNode headB) {
        //A B先递归到最后 在返回时一个一个比较
        if (headA.next == null && headB.next == null) {
            return;
        }else if(headA.next==null){
            dfs(headA,headB.next);
        }else if(headB.next==null){
            dfs(headA.next,headB);
        }else{
            dfs(headA.next,headB.next);
        }
    }*/

}
