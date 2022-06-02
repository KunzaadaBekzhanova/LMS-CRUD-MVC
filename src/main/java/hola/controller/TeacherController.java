package hola.controller;


import hola.models.Teacher;
import hola.service.CompanyService;
import hola.service.CourseService;
import hola.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
//@ComponentScan(basePackages = {"hola.controller"})
@RequestMapping("/teachers/{id}")
public class TeacherController {

    private final CourseService serviceCourse;
    private final CompanyService serviceCompany;
    private final TeacherService serviceTeacher;

    public TeacherController(CourseService serviceCourse, CompanyService serviceCompany, TeacherService serviceTeacher) {
        this.serviceCourse = serviceCourse;
        this.serviceCompany = serviceCompany;
        this.serviceTeacher = serviceTeacher;
    }

    @GetMapping("/newTeacher")
    public String newTeacher(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        model.addAttribute("courseName", teacher.getCourseName());
        return "teacher/newTeachers";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher, @ModelAttribute("courseName") String courseName, @PathVariable("id") Long id) {
        teacher.setCompany(serviceCompany.getCompanyById(id));
        teacher.setCourse(serviceCourse.getCourseByName(courseName));
        serviceTeacher.saveTeacher(teacher);
        return "redirect:/courses/{id}";
    }

    @DeleteMapping("/{idTeacher}/deleteTeacher")
    public String deleteTeacher(@PathVariable("idTeacher") Long id) {
        serviceTeacher.deleteTeacher(id);
        return "redirect:/courses/{id}";
    }

    @GetMapping("/{idTeacher}/editTeacher")
    public String editTeacher(Model model, @PathVariable("id") Long id, @PathVariable("idTeacher") Long idTeacher) {
        model.addAttribute("teacher", serviceTeacher.getTeacherById(idTeacher));
        model.addAttribute("courseId", id);
        return "teacher/editTeachers";
    }

    @PatchMapping("/{idTeacher}/updateTeacher")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher, @PathVariable("idTeacher") Long idTeacher) {
        serviceTeacher.updateTeacher(idTeacher, teacher);
        return "redirect:/courses/{id}/";
    }
}

