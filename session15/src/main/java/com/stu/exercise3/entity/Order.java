package com.stu.exercise3.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;

    private List<Product> products =
            new ArrayList<>();

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    // Thêm sản phẩm vào đơn
    public void addProduct(Product product)
    {
        products.add(product);
    }

    // Tính tổng tiền
    public double totalPrice()
    {
        double total = 0;

        for (Product product : products)
        {
            total += product.getPrice();
        }

        return total;
    }

    @Override
    public String toString() {

        return "Order{" +
                "orderId=" + orderId +
                ", products=" + products +
                ", total=" + totalPrice() +
                '}';
    }
}