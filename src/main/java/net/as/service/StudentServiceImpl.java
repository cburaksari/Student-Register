package net.as.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.RuntimeErrorException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.as.dao.StudentDAO;
import net.as.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;

	private static final Logger logger = Logger
			.getLogger(StudentServiceImpl.class.getName());

	public void addStudent(Student student) {
		try {
			studentDAO.addStudent(student);

		} catch (ConstraintViolationException e) {
			Set<ConstraintViolation<?>> constraintViolations = e
					.getConstraintViolations();
			for (ConstraintViolation<?> c : constraintViolations)
				logger.log(Level.SEVERE, c.getMessage());
			throw new ConstraintViolationException(constraintViolations);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	public List<Student> getStudents() {
		try {
			return studentDAO.getStudents();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	public void updateStudent(Student student) {
		try {
			studentDAO.updateStudent(student);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		}
	}

	public void deleteStudent(Student student) {
		try {
			studentDAO.deleteStudent(student);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		}
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

}
