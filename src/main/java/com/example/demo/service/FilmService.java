package com.example.demo.service;

import java.util.Collection;

import org.springframework.data.domain.Page;

import com.example.demo.model.Film;

public interface FilmService {

	Collection<Film> findAll();

	Film findOne(Long id);

	Film create(Film film);

	Film update(Film film);

	void delete(Long id);

	Page<Film> findFilms(int page, int size, String sortDirection, String sortParameter);
}
