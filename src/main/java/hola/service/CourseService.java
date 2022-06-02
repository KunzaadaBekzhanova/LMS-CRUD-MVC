package hola.service;

import hola.models.Course;
import hola.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class  CourseService{
        private CourseRepository courseRepository;

        @Autowired
        public CourseService(CourseRepository courseRepository) {
            this.courseRepository = courseRepository;
        }


        public void save(Course course) {
            courseRepository.save(course);
        }


        public List<Course> getCourses(Long id) {
            return courseRepository.getCourses(id);
        }
    public List<Course> getCourses() {
        return courseRepository.getCourses();
    }


        public Course getCourseById(Long id) {
            return courseRepository.getCourseById(id);
        }


        public void deleteCourse(Long id) {
            courseRepository.deleteCourse(id);
        }


        public void updateCourse(Long id, Course updatedCourse) {
            courseRepository.updateCourse(id, updatedCourse);
        }


        public Course getCourseByName(String name) {
            return courseRepository.getCourseByName(name);
        }


}

