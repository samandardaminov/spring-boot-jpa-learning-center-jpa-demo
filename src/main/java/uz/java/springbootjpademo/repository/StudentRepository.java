package uz.java.springbootjpademo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.java.springbootjpademo.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findAllByNameContainingIgnoreCase(String name);

    @Query(value = "select * from student where name like :ism", nativeQuery = true)
    List<Student> searchByName(@Param(value="ism")String name);
}
