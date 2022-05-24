package com.hkllyx.demo.basic.concurrent.others.simulation.restaurant;

/**
 * @author HKLLY
 * @date 2019/4/14
 */
public class Plate {
    private final Order order;
    private final Food food;

    public Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}
