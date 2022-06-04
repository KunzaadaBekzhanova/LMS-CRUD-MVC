package hola.repository;

import hola.models.Course;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@Repository
@Transactional
public class CourseRepository {

    //@PersistenceContext
    private final EntityManager entityManager;

    public CourseRepository(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.createEntityManager();
    }


    public void save(Course course) {
        entityManager.getTransaction().begin();
        entityManager.merge(course);
        entityManager.getTransaction().commit();
    }


    public List<Course> getCourses(Long id) {
        return entityManager.createQuery("SELECT c FROM Course c where c.company.id = :id", Course.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Course> getCourses() {
        return entityManager.createQuery("SELECT c FROM Course c ", Course.class).getResultList();
    }


    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }


    public void deleteCourse(Long id) {
        entityManager.remove(getCourseById(id));
    }


    public void updateCourse(Long id, Course updatedCourse) {
        Course course = getCourseById(id);
        course.setCourseName(updatedCourse.getCourseName());
        course.setDuration(updatedCourse.getDuration());
        entityManager.merge(course);
    }


    public Course getCourseByName(String name) {
        return (Course) entityManager.createQuery("select c from Course c where c.courseName like:name", Course.class).setParameter("name", name).getSingleResult();
    }
}