package com.code.demo.design.order;

/**
 * @Date 2021/3/5 16:19
 * @Author LBWNB
 **/
public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}
