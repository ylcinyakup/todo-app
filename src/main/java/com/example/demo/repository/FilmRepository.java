package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Film;

@Repository
public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {

}
