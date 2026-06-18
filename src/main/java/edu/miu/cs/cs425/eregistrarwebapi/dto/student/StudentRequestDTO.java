package edu.miu.cs.cs425.eregistrarwebapi.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record StudentRequestDTO(
        @NotBlank(message = "Student number cannot be null, blank or an empty string")
        String studentNumber,

        @NotBlank(message = "First name cannot be null, blank or an empty string")
        String firstName,

        String middleName,

        @NotBlank(message = "Last name cannot be null, blank or an empty string")
        String lastName,

        Double cgpa,

        @NotNull(message = "Enrollment date cannot be null")
        LocalDate enrollmentDate,

        @NotBlank(message = "International status cannot be null, blank or an empty string")
        @Pattern(regexp = "^(Yes|No)$", message = "isInternational must be 'Yes' or 'No'")
        String isInternational
) {
}
