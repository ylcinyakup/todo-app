package com.example.demo.service;

import java.util.Collection;

import com.example.demo.model.Film;

public interface FilmService {

	Collection<Film> findAll();

	Film findOne(Long id);

	Film create(Film film);

	Film update(Film film);

	void delete(Long id);
}
