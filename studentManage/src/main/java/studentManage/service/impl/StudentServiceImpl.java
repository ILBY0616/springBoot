package studentManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentManage.entity.Student;
import studentManage.mapper.StudentMapper;
import studentManage.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public int deleteStudentById(int id) {
        return studentMapper.deleteStudentById(id);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public Student findStudentById(int id) {
        return studentMapper.findStudentById(id);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentMapper.findAllStudent();
    }
}
