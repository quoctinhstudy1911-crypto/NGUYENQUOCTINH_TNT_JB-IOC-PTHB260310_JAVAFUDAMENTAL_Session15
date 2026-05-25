package com.stu.exercise1.service;

import com.stu.exercise1.entity.AppException;
import com.stu.exercise1.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie)
    {
        if(existsById(movie.getId()))
        {
            throw new AppException("ID bị trùng");
        }
        if(!isValidRating(movie.getRating()))
        {
            throw new AppException( "Rating phải từ 1 -> 10");
        }

        movies.add(movie);
    }

    private boolean isValidRating(int rating) {
        return rating >= 1 && rating <= 10;
    }

    private boolean existsById(int id ) {
      return movies.stream()
                .anyMatch(movie -> movie.getId() == id);
    }

    public boolean findByName(String name)
    {
        return movies.stream().anyMatch(movie -> movie.getTitle().equalsIgnoreCase(name));
    }

    public List<Movie> filterByRating()
    {
        return movies.stream()
                .filter(movie ->
                        movie.getRating() > 8)
                .toList();
    }

    public void updateMovie (Movie upMovie)
    {
        Movie currentMovie = movies.stream()
                .filter(movie -> movie.getId()==upMovie.getId())
                .findFirst()
                .orElseThrow(() ->
                        new AppException("ID KHÔNG TỒN TẠI")
                );

        if(!isValidRating(upMovie.getRating()))
        {
            throw new AppException(
                    "Rating phải từ 1 -> 10"
            );
        }

        currentMovie.setTitle(
                upMovie.getTitle()
        );

        currentMovie.setDirector(
                upMovie.getDirector()
        );

        currentMovie.setReleaseDate(
                upMovie.getReleaseDate()
        );

        currentMovie.setRating(
                upMovie.getRating()
        );

    }

    public void removeMovieById(int id)
    {
        Movie currentMovie = movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AppException("ID KHÔNG TỒN TẠI")
                );

        movies.remove(currentMovie);

        System.out.println("Xóa phim thành công");
    }

    public void displayMovies()
    {
        if(movies.isEmpty())
        {
            System.out.println("Danh sách phim trống");
            return;
        }
        movies.forEach(System.out::println);
    }

}
