package io.avec.vaadimbuilderdemo.data.service;

import io.avec.vaadimbuilderdemo.data.entity.Department;
import io.avec.vaadimbuilderdemo.data.entity.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByDepartment(Department department, Sort sort);

}