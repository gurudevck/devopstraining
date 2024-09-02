package com.hsbc.dao;

import com.hsbc.exception.DuplicateStudentException;
import com.hsbc.exception.NoSuchStudentException;
import com.hsbc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class StudentDaoJdbcImpl implements StudentDao{
    @Autowired
    private JdbcTemplate template;

    private JdbcClient client;
    @Override
    public boolean createStudent(Student student) throws DuplicateStudentException {
        String query = "Insert into Student(student_id, student_name, student_score, dept_id) values(?,?,?,?)";
        int result;
        try {
            result = template.update(query, student.getStudentId(), student.getStudentName(), student.getStudentScore(), student.getDept().getDeptId());
        } catch (RuntimeException e) {
            throw new DuplicateStudentException("duplicate student");
        }
//        int result = client.sql(query)
//                .param(student.getStudentId())
//                .param(student.getStudentName())
//                .param(student.getStudentScore())
//                .param(student.getDept().getDeptId()).update();
        if (result == 1)
            return true;
        else
            throw new DuplicateStudentException("duplicate student");
    }

    @Override
    public Student readStudentById(int studentId) throws NoSuchStudentException {
        String query = "select * from student where student_id = "+studentId;
        Student result = template.queryForObject(query, new StudentRowMapper());
        //Student result = (Student) client.sql(query).query().singleValue();
        if(result==null)
            throw new NoSuchStudentException("student not found");
        return result;
    }

    @Override
    public List<Student> readAllStudents() {
        String query = "select * from student";
        List<Student> students = template.query(query, new StudentRowMapper());
        return students;
    }

    @Override
    public boolean updateById(String new_name, int studentId) {
        String query = "update student set student_name = ? where student_id = ?";

        int result = template.update(query, new_name, studentId);
        return result == 1;
    }

    @Override
    public boolean deleteStudent(int studentId) {
        String query = "delete from student where student_id = "+studentId;
        int result = template.update(query);
        return result==1;
    }
}
