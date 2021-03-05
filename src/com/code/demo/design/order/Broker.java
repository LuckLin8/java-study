package com.code.demo.design.order;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/3/5 16:19
 * @Author LBWNB
 **/
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}