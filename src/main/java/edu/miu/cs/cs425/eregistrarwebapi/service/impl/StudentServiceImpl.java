package edu.miu.cs.cs425.eregistrarwebapi.service.impl;

import edu.miu.cs.cs425.eregistrarwebapi.dto.student.StudentRequestDTO;
import edu.miu.cs.cs425.eregistrarwebapi.dto.student.StudentResponseDTO;
import edu.miu.cs.cs425.eregistrarwebapi.exception.StudentNotFoundException;
import edu.miu.cs.cs425.eregistrarwebapi.model.Student;
import edu.miu.cs.cs425.eregistrarwebapi.repository.StudentRepository;
import edu.miu.cs.cs425.eregistrarwebapi.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .sorted(Comparator.comparing(StudentResponseDTO::lastName))
                .toList();
    }

    @Override
    public StudentResponseDTO addNewStudent(StudentRequestDTO request) {
        var newStudent = new Student(
                null,
                request.studentNumber(),
                request.firstName(),
                request.middleName(),
                request.lastName(),
                request.cgpa(),
                request.enrollmentDate(),
                request.isInternational()
        );
        var saved = studentRepository.save(newStudent);
        return toResponseDTO(saved);
    }

    @Override
    public StudentResponseDTO getStudentById(Integer id) throws StudentNotFoundException {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("Student with Id, %s not found", id)
                ));
        return toResponseDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Integer id, StudentRequestDTO request) throws StudentNotFoundException {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("Student with Id, %s not found", id)
                ));
        student.setStudentNumber(request.studentNumber());
        student.setFirstName(request.firstName());
        student.setMiddleName(request.middleName());
        student.setLastName(request.lastName());
        student.setCgpa(request.cgpa());
        student.setEnrollmentDate(request.enrollmentDate());
        student.setIsInternational(request.isInternational());
        studentRepository.save(student);
        return toResponseDTO(student);
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    private StudentResponseDTO toResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getStudentId(),
                student.getStudentNumber(),
                student.getFirstName(),
                student.getMiddleName(),
                student.getLastName(),
                student.getCgpa(),
                student.getEnrollmentDate(),
                student.getIsInternational()
        );
    }
}
