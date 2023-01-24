package uz.java.springbootjpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springbootjpademo.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Integer> {

}
