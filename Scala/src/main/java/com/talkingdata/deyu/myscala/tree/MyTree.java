package com.talkingdata.deyu.myscala.tree;

import java.util.LinkedList;
import java.util.Queue;

class ListNode {
    int val;
    ListNode left = null;
    ListNode right = null;

    ListNode(int val) {
        this.val = val;
    }
}

class Solution{
    public void leftView(ListNode root) {
        Queue<ListNode> qu1 = new LinkedList<ListNode>();
        Queue<ListNode> qu2 = new LinkedList<ListNode>();
        qu1.add(root);
        while (!qu1.isEmpty() || !qu2.isEmpty()) {
            Queue<ListNode> qu;
            Queue<ListNode> quTemp;
            if (qu1.isEmpty()) {
                qu = qu2;
                quTemp=qu1;
            } else {
                qu = qu1;
                quTemp=qu2;
            }
            // peek 获取不移除
            // pop 删除头
            System.out.println(qu.peek().val);
            while (!qu.isEmpty()) {
                // 返回第一个元素，并在队列中删除
                ListNode temp = qu.poll();
                if (temp.left != null) {
                    quTemp.add(temp.left);
                }
                if (temp.right != null) {
                    quTemp.add(temp.right);
                }
            }
        }
    }
}


public class MyTree {
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(5);
        ListNode r6 = new ListNode(6);
        ListNode r7 = new ListNode(7);
        ListNode r8 = new ListNode(8);
        ListNode r9 = new ListNode(9);
        ListNode r10 = new ListNode(10);
        ListNode r11 = new ListNode(11);
        ListNode r12 = new ListNode(12);
        ListNode r13 = new ListNode(13);
        ListNode r14 = new ListNode(14);
        ListNode r15 = new ListNode(15);
        root.left = r2;
        root.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;
        r4.left = r8;
        r4.right = r9;
        r9.left = r10;
        r6.left = r11;
        r7.right=r12;
        r12.right=r13;
        r13.right=r14;
        r14.right=r15;
        new Solution().leftView(root);
    }
}
