package com.assignment.student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Please provide a first name")
    @Size(min = 0, max = 30)
    private String firstName;

    @NotEmpty(message = "Please provide a last name")
    @Size(min = 0, max = 30)
    private String lastName;

    @NotNull(message = "Please provide a age")
    @Min(16)
    @Max(58)
    private int age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();
}
