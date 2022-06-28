package com.aspire.studentapi.services;

import com.aspire.studentapi.models.StudentModel;
import com.aspire.studentapi.repositories.StudentRepository;
import com.aspire.studentapi.utils.exceptions.APIUnprocessableEntity;
import com.aspire.studentapi.utils.exceptions.StudentNotFound;
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

    public StudentModel saveStudent(StudentModel student) throws APIUnprocessableEntity {
        try {
            return studentRepository.save(student);
        } catch (Exception exception) {
            throw new APIUnprocessableEntity("It was not possible to save the student, try again");
        }
    }

    public StudentModel updateStudent(Long id, StudentModel student) throws StudentNotFound {
        StudentModel studentToUpdate = null;
        if(!studentRepository.findById(id).isEmpty()){
            studentToUpdate = studentRepository.findById(id).orElseThrow(() -> new StudentNotFound("Student to update doesn't exist."));
            studentToUpdate.setName(student.getName());
            studentToUpdate.setEmail(student.getEmail());
            studentToUpdate.setPhone(student.getPhone());
            return studentRepository.save(studentToUpdate);
        }else{
            throw new StudentNotFound(("Student not found"));
        }
    }

    public Optional<StudentModel> getStudentById(Long id) throws StudentNotFound {
        if(!studentRepository.findById(id).isEmpty()){
            return studentRepository.findById(id);
        }else{
            throw new StudentNotFound(("Student not found"));
        }
    }

    public boolean deleteStudent(Long id){
        try {
            studentRepository.deleteById(id);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
