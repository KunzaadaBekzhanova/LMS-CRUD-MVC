package hola.service;

import hola.models.Teacher;
import hola.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    public void saveTeacher(Teacher teacher) {
        teacherRepository.saveTeacher(teacher);
    }


    public List<Teacher> getTeachers(Long id) {
        return teacherRepository.getTeachers(id);
    }


    public Teacher getTeacherById(Long id) {
        return teacherRepository.getTeacherById(id);
    }


    public void deleteTeacher(Long id) {
        teacherRepository.deleteTeacher(id);
    }


    public void updateTeacher(Long id, Teacher updatedTeacher) {
        teacherRepository.updateTeacher(id, updatedTeacher);
    }

}

