package net.as.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.as.model.Student;


public interface StudentService {

	public void addStudent(Student student);

	public void updateStudent(Student student);

	public List<Student> getStudents();

	public void deleteStudent(Student student);
}
