package com.example.demo.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Film;
import com.example.demo.repository.FilmRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FilmServiceImpl implements FilmService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FilmRepository filmRepository;

	@Override
	public Collection<Film> findAll() {
		Collection<Film> films = (Collection<Film>) filmRepository.findAll();
		return films;
	}
	
	@Override
	public List<Film> findFilms(int page, int size, String sortDirection, String sortParameter) {
		PageRequest request;
		if(sortDirection.equals("ASC")) {
			request = PageRequest.of(page - 1, size, Sort.Direction.ASC, sortParameter);
		}else {
			request = PageRequest.of(page - 1, size, Sort.Direction.DESC, sortParameter);
		}
		
		return filmRepository.findAll(request).getContent();
	}
	

	@Override
	public Film findOne(Long id) {
		Optional<Film> film = filmRepository.findById(id);
		return film.isPresent() ? film.get() : null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Film create(Film film) {
		if (film.getFilmId() != null) {
			logger.error("Attempted to create a Film, but id attribute was not null.");
			logger.info("< create");
			throw new EntityExistsException(
					"Cannot create new Film with supplied id.  The id attribute must be null to create an entity.");
		}
		Film savedFilm = filmRepository.save(film);
		return savedFilm;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Film update(Film film) {
		Optional<Film> filmToUpdate = filmRepository.findById(film.getFilmId());
		if (!filmToUpdate.isPresent()) {
			logger.error("Attempted to update a Film, but the entity does not exist.");
			logger.info("< update {}", film.getFilmId());
			throw new NoResultException("Requested Film not found.");
		}
		filmRepository.save(film);

		logger.info("< update {}", film.getFilmId());
		return film;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Long id) {
		filmRepository.deleteById(id);
	}

}
