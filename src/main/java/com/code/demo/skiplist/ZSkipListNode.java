package com.code.demo.skiplist;

/**
 * @Date 2021/2/5 9:17
 * @Author LBWNB
 **/
public class ZSkipListNode {

    //分值
    private Double score;

    //存储对象
    private Object obj;

    //后退指针
    private ZSkipListNode backward;

    private ZSkipListLevel[] zSkipListLevels;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public ZSkipListNode getBackward() {
        return backward;
    }

    public void setBackward(ZSkipListNode backward) {
        this.backward = backward;
    }

    public ZSkipListLevel[] getzSkipListLevels() {
        return zSkipListLevels;
    }

    public void setzSkipListLevels(ZSkipListLevel[] zSkipListLevels) {
        this.zSkipListLevels = zSkipListLevels;
    }
}

//层级对象
class ZSkipListLevel{

    //前进指针
    private ZSkipListNode forward;

    //跨度
    private Integer span;

    public ZSkipListNode getForward() {
        return forward;
    }

    public void setForward(ZSkipListNode forward) {
        this.forward = forward;
    }

    public Integer getSpan() {
        return span;
    }

    public void setSpan(Integer span) {
        this.span = span;
    }
}
