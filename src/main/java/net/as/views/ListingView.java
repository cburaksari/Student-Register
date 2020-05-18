package net.as.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.as.model.File;
import net.as.model.Student;
import net.as.service.StudentService;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean
@ViewScoped
@Component("list")
public class ListingView {

	@Autowired
	private StudentService studentService;
	
	private Student selectedStudent;
	
	private List<Student> students;
	
	private List<UploadedFile> files;
	
	private File file;
	
	private Map<String,String> districts;
	
	public void allStudents() {
		students = studentService.getStudents();
	}

	public void updateStudent(Student student) {
		if(files != null){
			List<File> fileList = new ArrayList<>();
			for(UploadedFile f : files){
				File file = new File();
				file.setFileData(f.getContent());
				file.setFileName(f.getFileName());
				file.setStudent(student);
				fileList.add(file);
			}
			student.setFiles(fileList);
		}
		studentService.updateStudent(student);
		allStudents();
		selectedStudent = null;
		files = null;
	}

	public void deleteStudent(Student student) {
		studentService.deleteStudent(student);
		allStudents();
	}

	public void onUpdate(RowEditEvent event) {
		updateStudent((Student) event.getObject());
	}
	
	public void handleFileUpload(Student student) {
		selectedStudent = student;
		
    }
	
	public void upload(FileUploadEvent event){
		if(files == null) {
			files = new ArrayList<>();
		}
		UploadedFile f = event.getFile();
		files.add(f);
	}
	
	public void onCityChange(Student student) {
		RegistrationView view;
        if(student.getCity() !=null && !student.getCity().equals("")){
        	view = RegistrationView.getInstance();
            view.init();
            districts = (Map<String, String>) RegistrationView.getInstance().getData().get(student.getCity());}
        else
            districts = new HashMap<String, String>();
    }
	
	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}


	public List<UploadedFile> getFiles() {
		return files;
	}

	public void setFiles(List<UploadedFile> files) {
		this.files = files;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Map<String, String> getDistricts() {
		return districts;
	}

	public void setDistricts(Map<String, String> districts) {
		this.districts = districts;
	}
	

}
