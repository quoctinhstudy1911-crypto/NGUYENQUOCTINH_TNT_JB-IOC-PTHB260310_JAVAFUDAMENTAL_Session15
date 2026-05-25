package com.stu.exercise3.service;

import com.stu.exercise3.entity.Order;
import com.stu.exercise3.entity.OrderNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderManager {

    private Map<String, Order> orders = new LinkedHashMap<>();

    // Tạo đơn hàng
    public void createOrder(String orderCode, Order order)
    {
        orders.put(orderCode, order);
    }

    // Lấy đơn hàng
    public Order getOrder(String orderCode) throws OrderNotFoundException
    {
        if(!orders.containsKey(orderCode))
        {
            throw new OrderNotFoundException("Không tìm thấy đơn hàng");
        }
        return orders.get(orderCode);
    }

    // Hiển thị đơn hàng
    public void displayOrders()
    {
        if(orders.isEmpty())
        {
            System.out.println("Danh sách đơn hàng trống");
            return;
        }

        for (Map.Entry<String, Order> entry : orders.entrySet())
        {
            System.out.println("Mã đơn: " + entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}