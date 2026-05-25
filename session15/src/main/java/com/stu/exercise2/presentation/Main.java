package com.stu.exercise2.presentation;

import com.stu.exercise2.entity.InvalidCreditsException;
import com.stu.exercise2.entity.Subject;
import com.stu.exercise2.service.SubjectManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void validateCredits(int credits) throws InvalidCreditsException {

        if(credits < 0 || credits > 10)
        {
            throw new InvalidCreditsException("Số tín chỉ không hợp lệ");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SubjectManager<Subject> manager = new SubjectManager<>();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm môn học");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Xóa môn học");
            System.out.println("4. Tìm theo tên");
            System.out.println("5. Lọc credits > 3");
            System.out.println("0. Thoát");

            System.out.print("Chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice)
            {
                case 1:
                    try {
                        System.out.print("Code: ");
                        String code = scanner.nextLine();

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Credits: ");
                        int credits = Integer.parseInt(scanner.nextLine());

                        validateCredits(credits);

                        System.out.print("Start Date (dd/MM/yyyy): ");

                        LocalDate startDate = LocalDate.parse(scanner.nextLine(), formatter);

                        Subject subject = new Subject(code, name, credits, startDate);
                        manager.addSubject(subject);
                        System.out.println("Thêm môn học thành công");

                    }
                    catch (InvalidCreditsException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    catch (Exception e)
                    {
                        System.out.println("Dữ liệu không hợp lệ");
                    }

                    break;

                case 2:

                    manager.displaySubjects();
                    break;
                case 3:

                    System.out.print("Nhập code cần xóa: ");
                    String code = scanner.nextLine();
                    manager.removeSubjectByCode(code);
                    break;

                case 4:

                    System.out.print("Nhập tên môn học: ");

                    String name = scanner.nextLine();
                    manager.findByName(name);
                    break;

                case 5:
                    manager.filterCreditsGreaterThan3();
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