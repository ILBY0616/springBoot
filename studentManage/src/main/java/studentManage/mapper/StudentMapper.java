package studentManage.mapper;

import org.apache.ibatis.annotations.Mapper;
import studentManage.entity.Student;

import java.util.List;

@Mapper
public interface StudentMapper {
    int addStudent(Student student);

    int deleteStudentById(int id);

    int updateStudent(Student student);

    Student findStudentById(int id);

    List<Student> findAllStudent();
}
