package org.perscholas.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


//Lombok
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//Database
@Entity
//Spring Boot
@Component
@Table(name = "course")
public class Course implements Serializable {
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
    @Column(name = "course_id")
    Long courseId;
    @Column(nullable = false, name = "course_name")
    String courseName;
    @Column(nullable = false, name = "course_instructor")
    String courseInstructor;

    @ToString.Exclude
    @ManyToMany
    @JoinTable (
        name="student_course",
        inverseJoinColumns=@JoinColumn(name = "student_id"),
        joinColumns=@JoinColumn(name = "course_id")
    )
    List<Course> students;

}
