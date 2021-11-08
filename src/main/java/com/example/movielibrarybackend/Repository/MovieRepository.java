package com.example.movielibrarybackend.Repository;

import com.example.movielibrarybackend.Model.Movie;
import org.springframework.data.repository.CrudRepository;


public interface MovieRepository extends CrudRepository <Movie, String> {


}
