package io.avec.vaadimbuilderdemo.data.service;

import io.avec.vaadimbuilderdemo.data.entity.Department;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.stream.Stream;

@Getter
@Service
public class DepartmentService extends CrudService<Department, Integer> {

    private final DepartmentRepository repository;

    public DepartmentService(@Autowired DepartmentRepository repository) {
        this.repository = repository;
    }

    public Stream<Department> fetch(String filter, int offset, int limit) {
        return repository.findAll().stream()
                .filter(department -> filter == null || department.toString()
                        .toLowerCase().startsWith(filter.toLowerCase()))
                .skip(offset).limit(limit);
    }

    public int count(String filter) {
        return (int) repository.findAll().stream()
                .filter(department -> filter == null || department.toString()
                        .toLowerCase().startsWith(filter.toLowerCase()))
                .count();

    }
}