package io.avec.vaadimbuilderdemo.data.service;

import io.avec.vaadimbuilderdemo.data.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class DepartmentService extends CrudService<Department, Integer> {

    private final DepartmentRepository repository;

    public DepartmentService(@Autowired DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    protected DepartmentRepository getRepository() {
        return repository;
    }

}
