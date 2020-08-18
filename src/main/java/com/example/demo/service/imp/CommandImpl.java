package com.example.demo.service.imp;

import com.example.demo.service.Command;
import com.example.demo.service.Stock;

public class CommandImpl implements Command {
    private Stock abcStock;

    public CommandImpl(Stock abcStock) {
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}
