package studentManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import studentManage.entity.Student;
import studentManage.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/addStudent")
    public int addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public int deleteStudentById(@PathVariable("id") int id) {
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/updateStudent")
    public int updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @GetMapping("/findStudentById/{id}")
    public Student findStudentById(@PathVariable("id") int id) {
        return studentService.findStudentById(id);
    }

    @GetMapping("/findAllStudent")
    public List<Student> findAllStudent() {
        return studentService.findAllStudent();
    }
}
