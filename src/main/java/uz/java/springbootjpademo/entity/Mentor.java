package uz.java.springbootjpademo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.java.springbootjpademo.entity.AbsEntity.AbsNameEntity;
import uz.java.springbootjpademo.entity.enams.MentorStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mentor extends AbsNameEntity {
    private String phoneNumber;

    @ManyToMany
    private List<LearningCenter> learningCenterList;

    @ManyToMany
    private  List<Course> courseList;

    @Enumerated(EnumType.STRING)
    private MentorStatus mentorStatus;
}
