package com.hsbc.service;


import com.hsbc.exception.DuplicateStudentException;
import com.hsbc.exception.NoSuchStudentException;
import com.hsbc.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    public boolean addStudent(Student student) throws DuplicateStudentException;
    public Student findStudentById(int studentId) throws NoSuchStudentException;
    public List<Student> findAllStudents();
    public boolean changeById(String new_name, int studentId);
    public boolean removeStudent(int studentId);
}
