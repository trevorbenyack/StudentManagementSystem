package org.perscholas.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.perscholas.models.Student;
import org.perscholas.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes({"student", "studentObject"})
@Controller
@RequestMapping("/student")
@AllArgsConstructor
@Log
public class StudentController {

    StudentService studentService;

    @ModelAttribute("studentObject")
    public String studentObject() {
        return "";
    }

    @GetMapping("/showStudentPage")
    public String showStudent( Model model, @SessionAttribute("studentObject") String studentEmail){

        Student student = studentService.getStudentByEmail(studentEmail);
        model.addAttribute("student", student);
        log.info("student email through session is " + studentEmail);
        System.out.println("\n\n\n" + student + "\n\n\n");
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
