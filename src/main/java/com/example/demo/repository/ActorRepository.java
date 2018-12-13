package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {

}
