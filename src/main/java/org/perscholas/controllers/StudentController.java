package org.perscholas.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.perscholas.models.Student;
import org.perscholas.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes({"student", "studentObject"})  //creates a student object that canbe called
@Controller
@RequestMapping("/student")
@AllArgsConstructor
@Log
public class StudentController {

    StudentService studentService;

    // creates student object that can remeber what was entered
    @ModelAttribute("studentObject")
    public String studentObject() {
        return "";
    }

    // takes in studentEmail from form,
    @GetMapping("/showStudentDetails")
    public String showStudentDetailsByEmail(@SessionAttribute("studentObject") String studentEmail, Model model){
        Student student = studentService.getStudentByEmail(studentEmail);  // uses sessions to take email from redirect
        model.addAttribute("student", student);
        log.info("student email through session is " + studentEmail);
        System.out.println("\n\n\n" + student + "\n\n\n");
        return "showStudent";
    }

    @GetMapping("/{studentId}")
    public String showStudentDetailsById(@PathVariable String studentId, Model model){
        Student student = studentService.getStudentbyId(Long.parseLong(studentId));
        model.addAttribute("student", student);
        log.info("student email through session is " + studentId);
        return "showStudent";
    }

    @GetMapping("/getStudentByEmailForm")
    public String getStudentByEmailForm(){
        return "getStudentByEmailForm";
    }

    @PostMapping("/studentByEmailForm")
    public String studentByEmail(@RequestParam String student_email, Model model){
        model.addAttribute("studentObject", student_email);
        log.info("student email is " + student_email);
        return "redirect:/student/showStudentPage" ;
    }
}
