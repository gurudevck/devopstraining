package com.hsbc.service;

import com.hsbc.dao.StudentDao;
import com.hsbc.exception.DuplicateStudentException;
import com.hsbc.exception.NoSuchStudentException;
import com.hsbc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Map;

@Service("service")
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDao dao = null;

    public void setDao(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean addStudent(Student student) throws DuplicateStudentException {
            return dao.createStudent(student);
    }

    @Override
    public Student findStudentById(int studentId) throws NoSuchStudentException {
        return dao.readStudentById(studentId);
    }

    @Override
    public List<Student> findAllStudents() {
        return dao.readAllStudents();
    }

    @Override
    public boolean changeById(String new_name, int studentId) {
        return dao.updateById(new_name, studentId);
    }

    @Override
    public boolean removeStudent(int studentId) {
        return dao.deleteStudent(studentId);
    }
}
