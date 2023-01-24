package uz.java.springbootjpademo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.java.springbootjpademo.entity.AbsEntity.AbsNameEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Region extends AbsNameEntity {

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "region",cascade = CascadeType.ALL)
    private List<District> districts;
}
