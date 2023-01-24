package uz.java.springbootjpademo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.java.springbootjpademo.entity.AbsEntity.AbsNameEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course extends AbsNameEntity {

    private Integer LessonAmount;
    private Integer lessonDuration;

    @ManyToOne(fetch = FetchType.LAZY)
    private RoadMap roadMap;

    @ManyToOne(fetch = FetchType.LAZY)
    private LearningCenter learningCenter;

    private Double price;
}
