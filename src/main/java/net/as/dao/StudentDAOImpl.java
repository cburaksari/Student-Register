package net.as.dao;

import java.util.List;

import net.as.model.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addStudent(Student student) {
		getSessionFactory().getCurrentSession().persist(student);
	}

	public void updateStudent(Student student) {
		getSessionFactory().getCurrentSession().update(student);
	}

	public List<Student> getStudents() {
		return (List<Student>)getSessionFactory().getCurrentSession().createQuery("FROM Student").list();
		}

	public void deleteStudent(Student student) {
		getSessionFactory().getCurrentSession().delete(student);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
