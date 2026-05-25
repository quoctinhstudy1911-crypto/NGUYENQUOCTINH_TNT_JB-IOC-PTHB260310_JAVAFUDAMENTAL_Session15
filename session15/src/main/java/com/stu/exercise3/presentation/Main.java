package com.stu.exercise3.presentation;

import com.stu.exercise3.entity.*;
import com.stu.exercise3.service.OrderManager;
import com.stu.exercise3.service.ProductManager;

import java.util.Scanner;

public class Main {

    public static void validatePrice(double price) throws InvalidPriceException
    {
        if(price <= 0)
        {
            throw new InvalidPriceException("Giá sản phẩm phải lớn hơn 0");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        OrderManager orderManager = new OrderManager();

        int choice;
        do {

            System.out.println("\n================ MENU ================");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xóa sản phẩm");
            System.out.println("3. Hiển thị sản phẩm");
            System.out.println("4. Tạo đơn hàng");
            System.out.println("5. Thêm sản phẩm vào đơn hàng");
            System.out.println("6. Hiển thị đơn hàng");
            System.out.println("0. Thoát");
            System.out.println("======================================");

            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice)
            {
                case 1:
                    try {
                        System.out.print("Id: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Price: ");
                        double price = Double.parseDouble(scanner.nextLine());

                        validatePrice(price);

                        Product product = new Product(id, name, price);

                        productManager.addProduct(product);

                        System.out.println("Thêm sản phẩm thành công");
                    }
                    catch (InvalidPriceException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    catch (Exception e)
                    {
                        System.out.println("Dữ liệu không hợp lệ");
                    }
                    break;

                case 2:

                    try {
                        System.out.print("Nhập id cần xóa: ");

                        int id = Integer.parseInt(scanner.nextLine());

                        productManager.removeProductById(id);

                        System.out.println("Xóa sản phẩm thành công");
                    }
                    catch (ProductNotFoundException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:

                    productManager.displayProducts();
                    break;

                case 4:
                    System.out.print("Nhập mã đơn hàng: ");
                    String orderCode = scanner.nextLine();
                    System.out.print("Nhập orderId: ");

                    int orderId = Integer.parseInt(scanner.nextLine());
                    Order order = new Order(orderId);
                    orderManager.createOrder(orderCode, order);
                    System.out.println("Tạo đơn hàng thành công");
                    break;

                case 5:

                    try {
                        System.out.print("Nhập mã đơn hàng: ");
                        String code = scanner.nextLine();
                        Order currentOrder = orderManager.getOrder(code);
                        System.out.print("Nhập id sản phẩm: ");
                        int productId = Integer.parseInt(scanner.nextLine());

                        Product product = productManager.findById(productId);

                        if(product == null)
                        {
                            System.out.println("Không tìm thấy sản phẩm");
                            break;
                        }
                        currentOrder.addProduct(product);
                        System.out.println("Thêm sản phẩm vào đơn hàng thành công");
                    }
                    catch (OrderNotFoundException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    orderManager.displayOrders();
                    break;

                case 0:

                    System.out.println("Thoát chương trình");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 0);
        scanner.close();
    }
}