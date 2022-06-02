package hola.repository;

import hola.models.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TeacherRepository {

    @PersistenceContext
    EntityManager entityManager;


    public void saveTeacher(Teacher teacher) {
       // entityManager.getTransaction().begin();
        entityManager.merge(teacher);
        //entityManager.getTransaction().commit();
    }


    public List<Teacher> getTeachers(Long id) {
        return entityManager.createQuery("select t from Teacher t where t.company.id = :id", Teacher.class)
                .setParameter(("id"), id)
                .getResultList();

    }


    public Teacher getTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }


    public void deleteTeacher(Long id) {
        entityManager.remove(getTeacherById(id));
    }


    public void updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher teacher = getTeacherById(id);
        teacher.setFirstName(updatedTeacher.getFirstName());
        teacher.setLastName(updatedTeacher.getLastName());
        teacher.setEmail(updatedTeacher.getEmail());
        entityManager.merge(teacher);
    }
}

