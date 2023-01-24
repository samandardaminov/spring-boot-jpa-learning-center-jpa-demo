package uz.java.springbootjpademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.java.springbootjpademo.entity.Student;
import uz.java.springbootjpademo.payload.ApiResponse;
import uz.java.springbootjpademo.payload.StudentDto;
import uz.java.springbootjpademo.repository.StudentRepository;
import uz.java.springbootjpademo.utils.CommonUtils;

import java.util.stream.Collectors;

@Service
public class StudentService {

    final
    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDto getStudentDtoFromStudent(Student student){
        StudentDto studentDto=new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setPhoneNumber(student.getPhoneNumber());
        return studentDto;
    }

    public ApiResponse saveOrEditStudent(StudentDto dto){
        try {
            Student student = new Student();
            if (dto.getId()!=null){
                student = studentRepository.findById(dto.getId())
                        .orElseThrow(() -> new IllegalStateException("Student with this id not found"));
            }
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setPhoneNumber(dto.getPhoneNumber());
            studentRepository.save(student);
            return new ApiResponse(dto.getId()!=null?"Edited":"Saved",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse getAllStudents(Integer page, Integer size) {
        Page<Student> studentPage = studentRepository.findAll(CommonUtils.getPageableByNameAsc(page, size));

        return new ApiResponse("Success",
                true,
                studentPage.getTotalElements(),
                studentPage.getTotalPages(),
                studentPage.getContent().stream().map(this::getStudentDtoFromStudent).collect(Collectors.toList()));
    }

    public ApiResponse getOneStudentById(Integer id) {
        try {
            Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student with this Id not found"));
            return new ApiResponse("Student found",
                    true,
                    getStudentDtoFromStudent(student));
        }catch (Exception e){
            return new ApiResponse("Student with this Id not found",false);
        }
    }

    public ApiResponse removeById(Integer id) {
        try {
            studentRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error in deleting process",false);
        }
    }
}
