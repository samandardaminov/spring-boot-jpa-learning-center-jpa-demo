package uz.java.springbootjpademo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.java.springbootjpademo.entity.AbsEntity.AbsNameEntity;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends AbsNameEntity {
    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$", message = "Phone number must be 13 digits.")
    private String phoneNumber;
}
