package edu.miu.cs.cs425.eregistrarwebapi.dto.student;

import java.time.LocalDate;

public record StudentResponseDTO(
        Integer studentId,
        String studentNumber,
        String firstName,
        String middleName,
        String lastName,
        Double cgpa,
        LocalDate enrollmentDate,
        String isInternational
) {
}
