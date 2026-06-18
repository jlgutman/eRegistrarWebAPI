package edu.miu.cs.cs425.eregistrarwebapi.controller;

import edu.miu.cs.cs425.eregistrarwebapi.dto.student.StudentRequestDTO;
import edu.miu.cs.cs425.eregistrarwebapi.dto.student.StudentResponseDTO;
import edu.miu.cs.cs425.eregistrarwebapi.exception.StudentNotFoundException;
import edu.miu.cs.cs425.eregistrarwebapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/eregistrar/api/student"})
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = {"/list"})
    public ResponseEntity<List<StudentResponseDTO>> listAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping(value = {"/register"})
    public ResponseEntity<StudentResponseDTO> register(
            @RequestBody @Valid StudentRequestDTO request) {
        return new ResponseEntity<>(studentService.addNewStudent(request), HttpStatus.CREATED);
    }

    @GetMapping(value = {"/get/{id}"})
    public ResponseEntity<StudentResponseDTO> getById(
            @PathVariable Integer id) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping(value = {"/update/{id}"})
    public ResponseEntity<StudentResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody @Valid StudentRequestDTO request) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    @DeleteMapping(value = {"/delete/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
