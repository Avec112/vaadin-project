package io.avec.vaadimbuilderdemo.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;

import io.avec.vaadimbuilderdemo.data.service.PersonRepository;
import io.avec.vaadimbuilderdemo.data.entity.Person;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@Slf4j
@SpringComponent
public class DataGenerator {

    @Bean
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

}