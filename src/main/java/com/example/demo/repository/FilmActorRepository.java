package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FilmActor;

public interface FilmActorRepository extends JpaRepository<FilmActor, Long> {

}
