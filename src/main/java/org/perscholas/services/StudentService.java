package org.perscholas.services;

import lombok.AllArgsConstructor;
import org.perscholas.dao.IStudentRepo;
import org.perscholas.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class StudentService {
    IStudentRepo sRepo;
    /*
            - add class annotations
            - add @Transactional on class or on each method
            - add crud methods
     */

    // Show
    public List<Student> getAllStudents(){
        //logic
        return sRepo.findAll();
    }

    // Find
    public Student getStudentbyId(Long id){
        return sRepo.findByStudentId(id);
    }
    public Student getStudentByEmail(String studentEmail){
        return sRepo.findByStudentEmail(studentEmail);
    }

    // Save
    public Student saveStudent(Student s){
        return sRepo.saveAndFlush(s);

    }

}
