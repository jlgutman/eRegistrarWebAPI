package edu.miu.cs.cs425.eregistrarwebapi.service;

import edu.miu.cs.cs425.eregistrarwebapi.dto.student.StudentRequestDTO;
import edu.miu.cs.cs425.eregistrarwebapi.dto.student.StudentResponseDTO;
import edu.miu.cs.cs425.eregistrarwebapi.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO addNewStudent(StudentRequestDTO request);
    StudentResponseDTO getStudentById(Integer id) throws StudentNotFoundException;
    StudentResponseDTO updateStudent(Integer id, StudentRequestDTO request) throws StudentNotFoundException;
    void deleteStudentById(Integer id);
}
