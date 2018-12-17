package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FilmActor;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, Long> {

}
