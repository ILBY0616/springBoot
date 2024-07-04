package studentManage.service;

import studentManage.entity.Student;

import java.util.List;

public interface StudentService {
    int addStudent(Student student);

    int deleteStudentById(int id);

    int updateStudent(Student student);

    Student findStudentById(int id);

    List<Student> findAllStudent();
}
