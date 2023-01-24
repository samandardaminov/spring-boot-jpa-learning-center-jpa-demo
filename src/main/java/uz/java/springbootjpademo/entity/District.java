package uz.java.springbootjpademo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.java.springbootjpademo.entity.AbsEntity.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class District extends AbsNameEntity {

    @ManyToOne
    private Region region;
}
