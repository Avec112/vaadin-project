package io.avec.vaadimbuilderdemo.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;

import io.avec.vaadimbuilderdemo.data.entity.Department;
import io.avec.vaadimbuilderdemo.data.service.DepartmentRepository;
import io.avec.vaadimbuilderdemo.data.service.PersonRepository;
import io.avec.vaadimbuilderdemo.data.entity.Person;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringComponent
public class DataGenerator {

    @Bean("loadPersons")
    public CommandLineRunner loadData(@Autowired PersonRepository personRepository) {
        return args -> {
            if (personRepository.count() != 0L) {
                log.info("Using existing database");
                return;
            }
            int seed = 123;

            log.info("Generating demo data");

            log.info("... generating 100 Person entities...");
            ExampleDataGenerator<Person> personRepositoryGenerator = new ExampleDataGenerator<>(Person.class);
            personRepositoryGenerator.setData(Person::setId, DataType.ID);
            personRepositoryGenerator.setData(Person::setFirstName, DataType.FIRST_NAME);
            personRepositoryGenerator.setData(Person::setLastName, DataType.LAST_NAME);
            personRepositoryGenerator.setData(Person::setEmail, DataType.EMAIL);
            personRepositoryGenerator.setData(Person::setPhone, DataType.PHONE_NUMBER);
            personRepositoryGenerator.setData(Person::setDateOfBirth, DataType.DATE_OF_BIRTH);
            personRepositoryGenerator.setData(Person::setOccupation, DataType.OCCUPATION);
            personRepository.saveAll(personRepositoryGenerator.create(10, seed));

            log.info("Generated demo data");
        };
    }

    @Bean("loadDepartments")
    public CommandLineRunner loadData(@Autowired DepartmentRepository departmentRepository) {
        return args -> {
            if (departmentRepository.count() != 0L) {
                log.info("Using existing database");
                return;
            }

            log.info("Generating demo data");
            log.info("... generating 4 Department entities...");
            List<Department> departments = Arrays.asList(
                    new Department("Accounting", "1"),
                    new Department("Sales", "2"),
                    new Department("Marketing", "3")
            );
            departmentRepository.saveAll(departments);

            log.info("Generated demo data");
        };
    }

}