package com.code.demo.skiplist;

/**
 * @Date 2021/2/5 9:16
 * @Author LBWNB
 **/
public class ZSkipList {

    private ZSkipListNode head;

    private ZSkipListNode tail;

    private Long length;

    int level;

    public ZSkipListNode getHead() {
        return head;
    }

    public void setHead(ZSkipListNode head) {
        this.head = head;
    }

    public ZSkipListNode getTail() {
        return tail;
    }

    public void setTail(ZSkipListNode tail) {
        this.tail = tail;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
