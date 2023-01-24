package uz.java.springbootjpademo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.java.springbootjpademo.entity.AbsEntity.AbsNameEntity;
import uz.java.springbootjpademo.entity.enams.StudyType;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
public class Group extends AbsNameEntity {

    @ManyToMany
    private List<Student> studentList;

    @ManyToMany
    private  List<Mentor> mentorList;

    @Enumerated(EnumType.STRING)
    private StudyType studyType;

    @ManyToOne
    private Course course;

    private Timestamp startDate;
    private Time starTime;

    @ManyToOne
    private Room room;
}
