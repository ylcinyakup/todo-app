package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FilmCategory;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Long> {

}
