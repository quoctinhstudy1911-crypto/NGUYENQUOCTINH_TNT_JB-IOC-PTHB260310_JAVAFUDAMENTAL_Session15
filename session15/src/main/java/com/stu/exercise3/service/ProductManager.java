package com.stu.exercise3.service;

import com.stu.exercise3.entity.Product;
import com.stu.exercise3.entity.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    private List<Product> products = new ArrayList<>();

    // Thêm sản phẩm
    public void addProduct(Product product)
    {
        products.add(product);
    }

    // Hiển thị sản phẩm
    public void displayProducts()
    {
        if(products.isEmpty())
        {
            System.out.println("Danh sách sản phẩm trống");
            return;
        }

        for (Product product : products)
        {
            System.out.println(product);
        }
    }

    // Xóa sản phẩm
    public void removeProductById(int id) throws ProductNotFoundException
    {
        boolean removed = false;
        for (Product product : products)
        {
            if(product.getId() == id)
            {
                products.remove(product);
                removed = true;
                break;
            }
        }

        if(!removed)
        {
            throw new ProductNotFoundException("Không tìm thấy sản phẩm");
        }
    }

    // Tìm sản phẩm theo id
    public Product findById(int id)
    {
        for (Product product : products)
        {
            if(product.getId() == id)
            {
                return product;
            }
        }
        return null;
    }
}