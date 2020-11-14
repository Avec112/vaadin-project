package io.avec.vaadimbuilderdemo.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;


//    @Override
//    public int hashCode() {
//        if (id != null) {
//            return id.hashCode();
//        }
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof AbstractEntity)) {
//            return false; // null or other class
//        }
//        AbstractEntity other = (AbstractEntity) obj;
//
//        if (id != null) {
//            return id.equals(other.id);
//        }
//        return super.equals(other);
//    }
}