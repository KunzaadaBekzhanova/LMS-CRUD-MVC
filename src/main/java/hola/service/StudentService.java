package hola.service;

import hola.models.Student;
import hola.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class StudentService{
    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student) {
        studentRepository.saveStudent(student);
    }


    public List<Student> getStudents(Long id) {
        return studentRepository.getStudents(id);
    }


    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }


    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
    }


    public void updateStudent(Long id, Student updatedStudent) {
        studentRepository.updateStudent(id, updatedStudent);
    }
}
