package uz.java.springbootjpademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.springbootjpademo.payload.ApiResponse;
import uz.java.springbootjpademo.payload.StudentDto;
import uz.java.springbootjpademo.service.StudentService;
import uz.java.springbootjpademo.utils.ApplicationConstants;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    final
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEditStudent(@RequestBody StudentDto studentDto) {
        ApiResponse apiResponse = studentService.saveOrEditStudent(studentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                        apiResponse.getMessage().equals("Saved") ? 201 : 202 : 409)
                .body(apiResponse);
    }

    @GetMapping("/getAllStudents")
    public HttpEntity<?> getAllStudents(@RequestParam(value = "page",
                                                defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                        @RequestParam(value = "size",
                                                defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE) Integer size
                                        ) {
        return ResponseEntity.ok(studentService.getAllStudents(page,size));
    }

    @GetMapping("/byId/{id}")
    public HttpEntity<?> getOneStudentById(@PathVariable(value = "id")Integer id){
        ApiResponse response = studentService.getOneStudentById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> removeStudentById(@PathVariable(value = "id")Integer id){
        ApiResponse response = studentService.removeById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
}
