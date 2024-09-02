package com.hsbc.controller;

import com.hsbc.exception.DuplicateStudentException;
import com.hsbc.exception.NoSuchStudentException;
import com.hsbc.model.Student;
import com.hsbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "students")
public class StudentRestController {
    @Autowired
    private StudentService service;

    //http://localhost:9091/students - Get
    @GetMapping(path = "")
    public List<Student> getAllStudents(){
        return service.findAllStudents();
    }

    //http://localhost:9091/students - Post
    @PostMapping(path = "")
    public ResponseEntity<String> addStudent(@RequestBody Student student) throws DuplicateStudentException {
        ResponseEntity<String> response = null;
        boolean result = service.addStudent(student);
        if (result)
            response = new ResponseEntity<>("student added", HttpStatus.CREATED);
        else
            response = new ResponseEntity<>("student not added", HttpStatus.BAD_REQUEST);
        return response;
    }

    ////http://localhost:9091/students/<studentId> - Get
    @GetMapping(path = "{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) throws NoSuchStudentException {
        return service.findStudentById(studentId);
    }

    @DeleteMapping(path="{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") int studentId) throws NoSuchStudentException {
        ResponseEntity<String> response = null;
        boolean result = service.removeStudent(studentId);
        if(result)
            response = new ResponseEntity<>("Student "+studentId+" is deleted", HttpStatus.OK);
        return response;
    }

    @ExceptionHandler(NoSuchStudentException.class)
    @ResponseStatus(reason ="student not found", code = HttpStatus.INTERNAL_SERVER_ERROR)//500
    public void handleException(){
        //log the exception
    }
}
