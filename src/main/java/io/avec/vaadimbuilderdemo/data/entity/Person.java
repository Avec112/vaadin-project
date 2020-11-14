package io.avec.vaadimbuilderdemo.data.entity;

import io.avec.vaadimbuilderdemo.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
//    private String occupation;

    @ManyToOne
    private Department department;

}
