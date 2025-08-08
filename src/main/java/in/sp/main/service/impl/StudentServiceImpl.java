package in.sp.main.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.dto.StudentDto;
import in.sp.main.entity.Student;
import in.sp.main.mapper.StudentMapper;
import in.sp.main.repository.StudentRepository;
import in.sp.main.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<StudentDto> getAllStudent() {
		
		List<Student> students = studentRepository.findAll();
		
		List<StudentDto> studentDtos= students.stream()
				.map((student)-> StudentMapper.mapToStudentDto(student))
				.collect(Collectors.toList());
				
		
		return studentDtos;
	}

	@Override
	public void createStudent(StudentDto studentDto) {
		
		Student student= StudentMapper.mapToStudent(studentDto);
		
		studentRepository.save(student);
	
	}

	@Override
	public StudentDto getStudent(Long id) {
		
		Student student= studentRepository.findById(id).get();
		
		StudentDto studentDto=StudentMapper.mapToStudentDto(student);
		
		return studentDto;
	}

	@Override
	public void updateStudent(StudentDto studentDto) {
		
		Student student=StudentMapper.mapToStudent(studentDto);
		
		studentRepository.save(student);
		
	}

	@Override
	public void deleteStudent(Long id) {
		
		studentRepository.deleteById(id);
		
	}

}
