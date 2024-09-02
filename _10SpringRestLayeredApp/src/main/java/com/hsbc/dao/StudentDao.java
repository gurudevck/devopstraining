package com.hsbc.dao;

import com.hsbc.exception.DuplicateStudentException;
import com.hsbc.exception.NoSuchStudentException;
import com.hsbc.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    public boolean createStudent(Student student) throws DuplicateStudentException;
    public Student readStudentById(int studentId) throws NoSuchStudentException;
    public List<Student> readAllStudents();
    public boolean updateById(String new_name,int studentId);
    public boolean deleteStudent(int studentId);
}
