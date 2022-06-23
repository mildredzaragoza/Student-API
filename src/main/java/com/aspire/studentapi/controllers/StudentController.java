package com.aspire.studentapi.controllers;

import com.aspire.studentapi.models.StudentModel;
import com.aspire.studentapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ArrayList<StudentModel> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping()
    public StudentModel saveStudent(@RequestBody StudentModel student){
        return studentService.saveStudent(student);
    }

    @GetMapping(path = "/{id}")
    public Optional<StudentModel> getStudentById(@PathVariable("id") Long id){
        return studentService.getStudentById(id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteStudent(@PathVariable Long id){
        boolean deleteStatus = studentService.deleteStudent(id);
        if(deleteStatus){
            return "Student deleted successfully";
        }else{
            return "Something went wrong, try again.";
        }
    }
}
