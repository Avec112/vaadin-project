package io.avec.vaadimbuilderdemo.data.entity;

import io.avec.vaadimbuilderdemo.data.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Department extends AbstractEntity {
    private String departmentName;
    private String floor;

    public Department(String departmentName, String floor) {
        this.departmentName = departmentName;
        this.floor = floor;
    }
}
