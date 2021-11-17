package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.Teacher;
import com.example.studentmanagement.service.TeacherService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        super();
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public String listTeachers(Model model, @Param("keyword") String keyword){
        List<Teacher> teachers = teacherService.getAllTeachers(keyword);
        model.addAttribute("teachers", teachers);
        model.addAttribute("keyword", keyword);

        return "teachers";
    }

    @GetMapping("/teachers/new")
    public String createTeacherForm(Model model){
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "create_teacher";
    }
    @PostMapping("/teachers")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher){
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/edit/{id}")
    public String editTeacherForm(@PathVariable Long id, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "edit_teacher";
    }

    @PostMapping("teachers/{id}")
    public String updateTeacher(@PathVariable Long id,
                                @ModelAttribute("teacher") Teacher teacher,
                                Model model) {
        Teacher existingTeacher = teacherService.getTeacherById(id);
        existingTeacher.setId(id);
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setAddress(teacher.getAddress());
        existingTeacher.setContactNumber(teacher.getContactNumber());
        existingTeacher.setTutorOfClass(teacher.getTutorOfClass());
        existingTeacher.setSubject(teacher.getSubject());

        teacherService.updateTeacher(existingTeacher);
        return "redirect:/teachers";
    }
    @GetMapping("/teachers/{id}")
    public String deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacherById(id);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/info/{id}")
    public String listTeachers(@PathVariable Long id,  Model model){
        model.addAttribute("teachers", teacherService.getTeacherById(id));
        return "teacher_info";
    }

}
