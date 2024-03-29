package com.kodilla.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class HamOrderDecorator extends AbstractPizzaOrderDecorator {

    public HamOrderDecorator(PizzaOrder pizzaOrder) {
        super(pizzaOrder);
    }

    @Override
    public BigDecimal getCost() {
        //cost = 5
        return super.getCost().add(new BigDecimal(5));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + ham";
    }
}
