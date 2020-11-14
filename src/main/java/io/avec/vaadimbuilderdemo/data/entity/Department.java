package io.avec.vaadimbuilderdemo.data.entity;

import io.avec.vaadimbuilderdemo.data.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Department extends AbstractEntity {
    private String departmentName;
    private String floor;

    @OneToMany(targetEntity = Person.class, mappedBy = "department")
    private List<Person> person;

    public Department(String departmentName, String floor) {
        this.departmentName = departmentName;
        this.floor = floor;
    }

    // Important do not remove since this is a display value
    @Override
    public String toString() {
        return departmentName;
    }
}
