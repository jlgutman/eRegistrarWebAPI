package edu.miu.cs.cs425.eregistrarwebapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(name = "student_number", nullable = false, unique = true, length = 15)
    @NotBlank(message = "Student number cannot be null, blank or an empty string")
    private String studentNumber;

    @Column(name = "first_name", nullable = false, length = 64)
    @NotBlank(message = "First name cannot be null, blank or an empty string")
    private String firstName;

    @Column(name = "middle_name", length = 64)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 64)
    @NotBlank(message = "Last name cannot be null, blank or an empty string")
    private String lastName;

    @Column(name = "cgpa")
    private Double cgpa;

    @Column(name = "enrollment_date", nullable = false)
    @NotNull(message = "Enrollment date cannot be null")
    private LocalDate enrollmentDate;

    @Column(name = "is_international", nullable = false, length = 3)
    @NotBlank(message = "International status cannot be null, blank or an empty string")
    @Pattern(regexp = "^(Yes|No)$", message = "isInternational must be 'Yes' or 'No'")
    private String isInternational;
}
