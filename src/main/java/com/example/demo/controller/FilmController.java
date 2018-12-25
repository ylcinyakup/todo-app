package com.example.demo.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Film;
import com.example.demo.service.FilmService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class FilmController {

	private static final Logger logger = LoggerFactory.getLogger(FilmController.class);

	@Autowired
	private FilmService filmService;

	@RequestMapping(value = "/allFilms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Film>> getFilms() {
		logger.info("> getFilms");
		Collection<Film> films = filmService.findAll();
		logger.info("< getFilms");
		return new ResponseEntity<Collection<Film>>(films, HttpStatus.OK);
	}

	@RequestMapping(value = "/films", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Film>> getFilms(
			@RequestParam(value = "page", required = true, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize,
			@RequestParam(value = "sortDirection", required = true, defaultValue = "DESC") String sortDirection,
			@RequestParam(value = "sortParameter", required = true, defaultValue = "filmId") String sortParameter) {
		System.out.println(page);
		logger.info("> getFilms");
		Collection<Film> films = filmService.findFilms(page, pageSize, sortDirection, sortParameter);
		logger.info("< getFilms");
		return new ResponseEntity<Collection<Film>>(films, HttpStatus.OK);
	}

	@RequestMapping(value = "/films/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> getFilm(@PathVariable("id") Long id) {
		logger.info("> getFilm");
		Film film = filmService.findOne(id);
		if (film == null) {
			logger.info("< getFilm");
			return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getFilm");
		return new ResponseEntity<Film>(film, HttpStatus.OK);
	}

	@RequestMapping(value = "/films", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> createFilm(@RequestBody Film film) {
		logger.info("> createFilm");
		Film savedFilm = filmService.create(film);
		logger.info("< createFilm");
		return new ResponseEntity<Film>(savedFilm, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/films", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> updateFilm(@RequestBody Film film) {
		logger.info("> updateFilm");
		Film updatedFilm = filmService.update(film);
		if (updatedFilm == null) {
			logger.info("< updateFilm");
			return new ResponseEntity<Film>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("< updateFilm");
		return new ResponseEntity<Film>(updatedFilm, HttpStatus.OK);
	}

	@RequestMapping(value = "/films/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> deleteFilm(@PathVariable("id") Long id, @RequestBody Film film) {
		logger.info("> deleteFilm");
		filmService.delete(id);
		logger.info("< deleteFilm");
		return new ResponseEntity<Film>(HttpStatus.NO_CONTENT);

	}

}
