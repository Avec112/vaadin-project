package io.avec.vaadimbuilderdemo.data.entity;

import javax.persistence.Entity;

import io.avec.vaadimbuilderdemo.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Person extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String occupation;

}
