package io.avec.vaadimbuilderdemo.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;
import io.avec.vaadimbuilderdemo.data.entity.Department;
import io.avec.vaadimbuilderdemo.data.entity.Person;
import io.avec.vaadimbuilderdemo.data.service.DepartmentRepository;
import io.avec.vaadimbuilderdemo.data.service.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringComponent
@Profile("dev")
public class DataGenerator {


    @Bean
    public CommandLineRunner loadData(DepartmentRepository departmentRepository,
                                      PersonRepository personRepository) {
        return args -> {
            List<Department> departments = Arrays.asList(
                    new Department("Accounting", "1"),
                    new Department("Sales", "2"),
                    new Department("Marketing", "3")
            );
            departmentRepository.saveAll(departments);


            Person carl = new Person();
            carl.setFirstName("Carl");
            carl.setDepartment(departments.get(1));
            personRepository.save(carl);

            Person susan = new Person();
            susan.setFirstName("Susan");
            susan.setDepartment(departments.get(1));
            personRepository.save(susan);

            Person ann = new Person();
            ann.setFirstName("Ann");
            ann.setDepartment(departments.get(2));
            personRepository.save(ann);

        };
    }


}