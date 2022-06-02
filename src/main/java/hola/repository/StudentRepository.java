package hola.repository;


import hola.models.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    @PersistenceContext
    EntityManager entityManager;


    public void saveStudent(Student student) {
        //entityManager.getTransaction().begin();
        entityManager.merge(student);
        //entityManager.getTransaction().commit();
    }

    public List<Student> getStudents(Long id) {
        return entityManager.createQuery("select s from Student s where s.groups.id = :id",Student.class)//"select t from Teacher t where t.company.id = :id"
                .setParameter("id",id)
                .getResultList();
    }


    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }


    public void deleteStudent(Long id) {
        entityManager.remove(getStudentById(id));
    }

    public void updateStudent(Long id, Student updatedStudent) {
        Student student = getStudentById(id);
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setStudyFormat(updatedStudent.getStudyFormat());
        entityManager.merge(student);
    }
}
