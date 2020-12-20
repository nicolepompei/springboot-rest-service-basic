package com.in28minutes.springboot.rest.example.springboot2restservicebasic.Controller;

import com.in28minutes.springboot.rest.example.springboot2restservicebasic.Entity.Student;
import com.in28minutes.springboot.rest.example.springboot2restservicebasic.Exceptions.StudentNotFoundException;
import com.in28minutes.springboot.rest.example.springboot2restservicebasic.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> retrieveAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student retrieveStudent(@PathVariable long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);

        if(!student.isPresent())
            throw new StudentNotFoundException("id-" + id);

        return student.get();
    }

    @DeleteMapping("students/{id}")
    public void deleteStudent(@PathVariable long id){
        studentRepository.deleteById(id);
    }
}
