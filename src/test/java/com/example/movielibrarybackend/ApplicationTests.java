package com.example.movielibrarybackend;

import com.example.movielibrarybackend.Model.Movie;
import com.example.movielibrarybackend.Repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.http.MediaType;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;


import javax.transaction.Transactional;


import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {
    @Autowired
    MockMvc mvc;

    @Autowired
	MovieRepository newMovieRepository;

    @BeforeEach
    public void setupTest() {
        Movie item1 = new Movie();
        item1.setId("tt0110413");
        this.newMovieRepository.save(item1);
    }

    @Transactional
    @Rollback
    @Test
    void testGetAllItems() throws Exception {
        Movie item2 = new Movie();
        item2.setId("tt0110412");
        this.newMovieRepository.save(item2);

        this.mvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("tt0110412")));
    }

    @Transactional
    @Rollback
    @Test
    void testCreateNewTodoItem() throws Exception {
        String newTodoItem = "{\"id\":\"tt0110414\"}";

        this.mvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTodoItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("tt0110414")));
    }


    @Transactional
    @Rollback
    @Test
    void testDeleteSingleItem() throws Exception {
        Movie item2 = new Movie();
        item2.setId("tt0110415");
        this.newMovieRepository.save(item2);
        String testId = item2.getId();

        this.mvc.perform(delete(String.format("/api/movies/%s", testId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("tt0110415")));
    }
}
