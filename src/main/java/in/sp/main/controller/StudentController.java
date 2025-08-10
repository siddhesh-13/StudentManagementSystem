package in.sp.main.controller;

import java.util.List;
import java.util.logging.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.sp.main.dto.StudentDto;
import in.sp.main.service.StudentService;
import jakarta.validation.Valid;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
    public String home() {
        return "redirect:/students"; // Redirects to your students list
    }
	
//	Handler method to handle all list students request
	@GetMapping("/students")
	public String getAllStudentMethod(Model model) {
		
		List<StudentDto> students=studentService.getAllStudent();
		
		model.addAttribute("students", students);
		
		return "students";
	}
	
//	handler method to handle new student request
	@GetMapping("/students/new")
	public String newStudent(Model model) {
		
//		student model object to store student form data
		StudentDto studentDto=new StudentDto();
		
		model.addAttribute("student", studentDto);
		
		return "create_Student";
	}
	
	
//	handler method to handle save student form submit request
	@PostMapping("/students")
	public String createStudent(@Valid @ModelAttribute("student") StudentDto studentDto,
								BindingResult bindingResult,
								Model model
			) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("student", studentDto);
			return "create_Student";
		}
		
		studentService.createStudent(studentDto);
		
		return "redirect:/students";
	}
	
//	handler method to handle edit student form
	@GetMapping("/students/{id}/edit")
	public String editStudent(@PathVariable Long id, Model model) {
		
		StudentDto studentDto= studentService.getStudent(id);
		model.addAttribute("student",studentDto);
		
		return "edit_student";
	}
	
//	handler method to handle edit student form submit request
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, 
								@Valid @ModelAttribute("student") StudentDto studentDto,
								BindingResult bindingResult,
								Model model								
			) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("student", studentDto);
			return "edit_student";
		}
		
		studentDto.setId(id);
		studentService.updateStudent(studentDto);
		
		return "redirect:/students";
	}
	
//	handler method to handle delete student request
	@GetMapping("/students/{id}/delete")
	public String deleteStudent(@PathVariable Long id) {
		
		studentService.deleteStudent(id);
		
		return "redirect:/students";
	}
	
//	handler method to handle view student request
	@GetMapping("/students/{id}/view")
	public String viewStudent(@PathVariable Long id, Model model) {
		
		StudentDto studentDto= studentService.getStudent(id);
		model.addAttribute("student",studentDto);
		
		return "view_student";
	}
}
