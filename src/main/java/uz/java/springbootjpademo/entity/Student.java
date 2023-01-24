package uz.java.springbootjpademo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.java.springbootjpademo.entity.AbsEntity.AbsNameEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends AbsNameEntity {
    @Email
    private String email;

    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$", message = "Phone number must be 13 digits.")
    private String phoneNumber;

    @ManyToMany
    private List<Course> courses;

}
