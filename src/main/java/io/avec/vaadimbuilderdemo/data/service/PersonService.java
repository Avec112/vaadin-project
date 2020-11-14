package io.avec.vaadimbuilderdemo.data.service;

import io.avec.vaadimbuilderdemo.data.entity.Person;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;


@Getter
@Service
public class PersonService extends CrudService<Person, Integer> {

    private final PersonRepository repository;
    private final DepartmentService departmentService;

    public PersonService(@Autowired PersonRepository repository, DepartmentService departmentService) {
        this.repository = repository;
        this.departmentService = departmentService;
    }


//    @Override
//    protected PersonRepository getRepository() {
//        return repository;
//    }

}
