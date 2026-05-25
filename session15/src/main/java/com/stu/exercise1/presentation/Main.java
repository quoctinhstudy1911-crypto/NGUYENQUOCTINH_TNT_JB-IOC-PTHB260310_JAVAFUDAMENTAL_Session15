package com.stu.exercise1.presentation;

import com.stu.exercise1.entity.AppException;
import com.stu.exercise1.entity.Movie;
import com.stu.exercise1.service.MovieManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MovieManager manager = new MovieManager();

        int choice;
        do {

            System.out.println("\n========== MENU ==========");
            System.out.println("1. Thêm phim");
            System.out.println("2. Sửa phim");
            System.out.println("3. Xóa phim");
            System.out.println("4. Hiển thị phim");
            System.out.println("5. Tìm phim theo tên");
            System.out.println("6. Lọc phim rating > 8");
            System.out.println("0. Thoát");

            System.out.print("Lựa chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice)
            {
                case 1:

                    try {

                        System.out.print("Nhập id: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nhập title: ");
                        String title = scanner.nextLine();

                        System.out.print("Nhập director: ");
                        String director = scanner.nextLine();

                        System.out.print("Nhập release date (yyyy-MM-dd): ");
                        LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

                        System.out.print("Nhập rating: ");
                        int rating = Integer.parseInt(scanner.nextLine());

                        Movie movie = new Movie(id, title, director, releaseDate, rating);
                        manager.addMovie(movie);
                        System.out.println("Thêm phim thành công");
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Sai định dạng số");
                    }
                    catch (DateTimeParseException e)
                    {
                        System.out.println("Sai định dạng ngày");
                    }
                    catch (AppException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:

                    try {
                        System.out.print("Nhập id phim cần sửa: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nhập title mới: ");
                        String title = scanner.nextLine();

                        System.out.print("Nhập director mới: ");
                        String director = scanner.nextLine();

                        System.out.print("Nhập release date mới (yyyy-MM-dd): ");
                        LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

                        System.out.print("Nhập rating mới: ");
                        int rating = Integer.parseInt(scanner.nextLine());

                        Movie upMovie = new Movie(id, title, director, releaseDate, rating);
                        manager.updateMovie(upMovie);
                        System.out.println("Cập nhật phim thành công");
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Sai định dạng số");
                    }
                    catch (DateTimeParseException e)
                    {
                        System.out.println("Sai định dạng ngày");
                    }
                    catch (AppException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:

                    try {
                        System.out.print("Nhập id phim cần xóa: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        manager.removeMovieById(id);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("ID phải là số");
                    }
                    catch (AppException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:

                    manager.displayMovies();
                    break;

                case 5:

                    System.out.print("Nhập tên phim: ");
                    String title = scanner.nextLine();

                    boolean result = manager.findByName(title);

                    if(result)
                    {
                        System.out.println("Tìm thấy phim");
                    }
                    else
                    {
                        System.out.println("Không tìm thấy phim");
                    }

                    break;

                case 6:

                    List<Movie> movieList = manager.filterByRating();

                    if(movieList.isEmpty())
                    {
                        System.out.println("Không có phim phù hợp");
                    }
                    else
                    {
                        movieList.forEach(System.out::println);
                    }

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