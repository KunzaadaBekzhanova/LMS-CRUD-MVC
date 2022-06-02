package hola.controller;

import hola.models.Student;
import hola.service.GroupService;
import hola.service.StudentService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@ComponentScan(basePackages = {"hola.controller"})
@RequestMapping("/students/{id}")
public class StudentController {

    private final StudentService serviceStudent;
    private final GroupService serviceGroup;

    public StudentController(StudentService serviceStudent, GroupService serviceGroup) {
        this.serviceStudent = serviceStudent;
        this.serviceGroup = serviceGroup;
    }

   // @Autowired
    @GetMapping("/newStudent")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/newStudents";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student, @PathVariable("id") Long id) {
        student.setGroups(serviceGroup.getGroupById(id));
        serviceStudent.saveStudent(student);
        return "redirect:/students/{id}";
    }

    @GetMapping
    public String getStudents(Model model, @PathVariable("id") Long id) {
        model.addAttribute("studentLists", serviceStudent.getStudents(id));
        model.addAttribute("groupId", id);
        return "student/getStudents";
    }

    @DeleteMapping("/{idStudent}/deleteStudent")
    public String deleteStudent(@PathVariable("idStudent") Long id) {
        serviceStudent.deleteStudent(id);
        return "redirect:/students/{id}";
    }

    @GetMapping("/{idStudent}/editStudent")
    public String editStudent(Model model, @PathVariable("idStudent") Long idStudent,@PathVariable ("id")Long id){
        model.addAttribute("student", serviceStudent.getStudentById(idStudent));
        model.addAttribute("groupId", id);
        return "student/editStudents";
    }

    @PatchMapping("/{idStudent}/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student, @PathVariable("idStudent") Long idStudent){
        serviceStudent.updateStudent(idStudent,student);
        return "redirect:/students/{id}";
    }
}