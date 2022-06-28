package com.aspire.studentapi.controllers;

import com.aspire.studentapi.models.StudentModel;
import com.aspire.studentapi.services.StudentService;
import com.aspire.studentapi.utils.exceptions.APIUnprocessableEntity;
import com.aspire.studentapi.utils.exceptions.StudentNotFound;
import com.aspire.studentapi.utils.validator.StudentValidatorImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentValidatorImpl studentValidator;
    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found students"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Did not find any student"),
    })
    @GetMapping
    public ArrayList<StudentModel> getAllStudents(){
        return studentService.getAllStudents();
    }

    @Operation(summary = "Save student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student saved"),
            @ApiResponse(responseCode = "422", description = "Student's data is incomplete"),
    })
    @PostMapping
    public StudentModel saveStudent(@RequestBody StudentModel student) throws APIUnprocessableEntity {
        studentValidator.validator(student);
        return studentService.saveStudent(student);
    }

    @Operation(summary = "Get a student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
    })
    @GetMapping(path = "/student/{id}")
    public Optional<StudentModel> getStudentById(@Parameter(description = "id of student to be searched")@PathVariable("id") Long id) throws StudentNotFound {
        return studentService.getStudentById(id);
    }

    @Operation(summary = "Update a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated"),
            @ApiResponse(responseCode = "404", description = "Student to update not found"),
    })
    @PutMapping("/update/{id}")
    public StudentModel updateStudent(@Parameter(description = "id of student to be updated")
                                          @PathVariable("id") Long id, @RequestBody StudentModel student) throws APIUnprocessableEntity, StudentNotFound {
        studentValidator.validator(student);
        return studentService.updateStudent(id, student);
    }

    @Operation(summary = "Delete student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted"),
            @ApiResponse(responseCode = "404", description = "Student to delete not found"),
    })
    @DeleteMapping(path = "/delete/{id}")
    public String deleteStudent(@Parameter(description = "id of student to be deleted") @PathVariable Long id) throws StudentNotFound {
        boolean deleteStatus = studentService.deleteStudent(id);
        if(deleteStatus){
            return "Student deleted successfully";
        }else{
            throw new StudentNotFound(("Student not found"));
        }
    }
}
