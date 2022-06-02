package hola.controller;

import hola.models.Course;
import hola.service.CompanyService;
import hola.service.CourseService;
import hola.service.GroupService;
import hola.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@ComponentScan(basePackages = {"hola.controller"})
@RequestMapping("/courses/{id}")
public class CourseController {

    private final CourseService serviceCourse;
    private final CompanyService serviceCompany;
    private final GroupService serviceGroup;
    private final TeacherService serviceTeacher;

    @Autowired
    public CourseController(CourseService serviceCourse, CompanyService serviceCompany,
                            GroupService serviceGroup, TeacherService serviceTeacher) {
        this.serviceCourse = serviceCourse;
        this.serviceCompany = serviceCompany;
        this.serviceGroup = serviceGroup;
        this.serviceTeacher = serviceTeacher;
    }

    @GetMapping
    public String getCourses(Model model, @PathVariable("id") Long id) {
        System.out.println("attempt 1");
        model.addAttribute("courseLists", serviceCourse.getCourses(id));
        System.out.println("attempt 2");
        model.addAttribute("companyId", id);
        System.out.println("attempt 3");
        model.addAttribute("groupLists", serviceGroup.getGroupByCompanyId(id));
        System.out.println("attempt 4");
        model.addAttribute("teacherLists", serviceTeacher.getTeachers(id));
        return "course/getCourses";
    }
//    @ModelAttribute("courseLists")
//    public List<Course> findAllCourses() {
//        return serviceCourse.getCourses();
//    }
//    @GetMapping
//    public String getAll(@PathVariable ("id") Long id) {
//        return "course/getCourses";
//    }

    @GetMapping("/newCourse")
    public String newCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course/newCourses";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course, @PathVariable("id") Long id) {
        course.setCompany(serviceCompany.getCompanyById(id));
        serviceCourse.save(course);
        return "redirect:/courses/{id}";
    }

    @GetMapping("/{idCourse}/editCourse")
    public String editCourse(Model model, @PathVariable("id") Long id,@PathVariable("idCourse")Long idCourse) {
        model.addAttribute("course", serviceCourse.getCourseById(idCourse));
        model.addAttribute("companyId", id);
        return "course/editCourses";
    }

    @PatchMapping("/{idCourse}/updateCourse")
    public String updateCourse(@ModelAttribute("course") Course course, @PathVariable("idCourse") Long idCourse) {
        serviceCourse.updateCourse(idCourse, course);
        return "redirect:/courses/{id}";
    }

    @DeleteMapping("/{idCourse}/deleteCourse")
    public String deleteCourse(@PathVariable("idCourse") Long id) {
        serviceCourse.deleteCourse(id);
        return "redirect:/courses/{id}";
    }

}
