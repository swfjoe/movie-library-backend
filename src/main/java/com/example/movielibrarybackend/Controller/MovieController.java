package com.example.movielibrarybackend.Controller;

import com.example.movielibrarybackend.Model.Movie;
import com.example.movielibrarybackend.Repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MovieController {
    MovieRepository newMovieRepository;

    public MovieController(MovieRepository newMovieRepository) {
        this.newMovieRepository = newMovieRepository;
    }

    @GetMapping("/api/movies")
    public Iterable<Movie> getAllMovies() {
        return this.newMovieRepository.findAll();
    }

    @GetMapping("/api/movies/{id}")
    public Movie getSingleMovie(@PathVariable String id) {
        return this.newMovieRepository.findById(id).get();
    }

    @PostMapping("/api/movies")
    public Movie addNewMovie(@RequestBody Movie newMovie) {
        return this.newMovieRepository.save(newMovie);
    }

    @DeleteMapping("/api/movies/{id}")
    public Movie deleteMovie(@PathVariable String id) {
        Movie toDelete = this.newMovieRepository.findById(id).get();
        this.newMovieRepository.deleteById(id);
        return toDelete;
    }
}
