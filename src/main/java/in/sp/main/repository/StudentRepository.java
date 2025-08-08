package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
