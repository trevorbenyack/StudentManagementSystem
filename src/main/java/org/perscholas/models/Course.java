package org.perscholas.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


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
    Long cId;

    @ToString.Exclude
    @ManyToMany
    @JoinTable (
        name="student_course",
        inverseJoinColumns=@JoinColumn(name = "student_id"),
        joinColumns=@JoinColumn(name = "course_id")
    )
    List<Course> students;

}
