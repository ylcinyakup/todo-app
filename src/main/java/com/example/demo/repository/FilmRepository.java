package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {

}
