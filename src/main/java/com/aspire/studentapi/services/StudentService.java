package com.aspire.studentapi.services;

import com.aspire.studentapi.models.StudentModel;
import com.aspire.studentapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public ArrayList<StudentModel> getAllStudents(){
        return (ArrayList<StudentModel>) studentRepository.findAll();
    }

    public StudentModel saveStudent(StudentModel student){
        return studentRepository.save(student);
    }

    public Optional<StudentModel> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    public boolean deleteStudent(Long id){
        try{
            studentRepository.deleteById(id);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
