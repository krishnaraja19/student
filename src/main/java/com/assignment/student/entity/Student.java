package com.assignment.student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 0, max = 30)
    private String firstName;
    @NotBlank
    @Size(min = 0, max = 30)
    private String lastName;

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
