package io.avec.vaadimbuilderdemo.data.service;

import io.avec.vaadimbuilderdemo.data.entity.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class PersonService extends CrudService<Person, Integer> {

    private final PersonRepository repository;

    public PersonService(@Autowired PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    protected PersonRepository getRepository() {
        return repository;
    }

}
