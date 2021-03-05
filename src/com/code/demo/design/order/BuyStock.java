package com.code.demo.design.order;

/**
 * @Date 2021/3/5 16:18
 * @Author LBWNB
 **/
public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}