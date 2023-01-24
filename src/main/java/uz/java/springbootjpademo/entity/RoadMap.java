package uz.java.springbootjpademo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.java.springbootjpademo.entity.AbsEntity.AbsNameEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoadMap extends AbsNameEntity {

    @ManyToMany
    @JoinTable(name = "subject_center", joinColumns = {@JoinColumn(name = "subject_id")},
            inverseJoinColumns = {@JoinColumn(name = "center_id")})
    private List<LearningCenter> learningCenterList;
}
