package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//lombok
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//database
@Entity
//springboot
@Component
@Table(name = "student")
public class Student implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;

    /*
            note use annotation  '@ToString.Exclude' for Lists
            ----------------
            - add validation for fields
            - relationships
            - utilities methods if any
     */

    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "student_id")
    Long studentId;

    @Column(nullable = false, unique = true, name = "student_email")
    String studentEmail;

    @Column(nullable = false, name = "student_name")
    String studentName;

    @Column(nullable = false, name = "student_password")
    String studentPassword;

    @ToString.Exclude
    @ManyToMany
    @JoinTable
    (
        name= "student_course",
        joinColumns=@JoinColumn(name = "student_id"),
        inverseJoinColumns=@JoinColumn( name = "course_id")
    )
    List<Course> courses;



}
