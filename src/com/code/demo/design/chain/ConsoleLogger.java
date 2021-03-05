package com.code.demo.design.chain;

/**
 * @Date 2021/3/5 16:30
 * @Author LBWNB
 **/
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}