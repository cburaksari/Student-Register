package net.as.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.as.model.Student;


public interface StudentDAO {

	public void addStudent(Student student);
	
	public void updateStudent(Student student);
	
	public List<Student> getStudents();
	
	public void deleteStudent(Student student);
	
}
