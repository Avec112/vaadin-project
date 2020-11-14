package io.avec.vaadimbuilderdemo.data.service;

import io.avec.vaadimbuilderdemo.data.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}