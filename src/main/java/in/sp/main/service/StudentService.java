package in.sp.main.service;

import java.util.List;

import in.sp.main.dto.StudentDto;
import in.sp.main.entity.Student;

public interface StudentService {

	List<StudentDto> getAllStudent();
	void createStudent(StudentDto studentDto);
	StudentDto getStudent(Long id);
	void updateStudent(StudentDto studentDto);
	void deleteStudent(Long id);
}
