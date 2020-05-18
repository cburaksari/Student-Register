package net.as.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.as.model.File;
import net.as.model.Student;
import net.as.service.StudentService;

import org.hsqldb.lib.HashSet;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@ManagedBean
@ViewScoped
@Component("register")
public class RegistrationView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ListingView listView;
	
	
	private String name;
	
	private String surName;
	
	private String phoneNumber;
	
	private String city;
	
	private String district;
	
	private String description;
	
	private UploadedFiles files;
	
	private Map<String,String> cities;
	
	private Map<String,String> districts;
	
	private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
	
	private static final RegistrationView viewObject = new RegistrationView();
	
	private RegistrationView(){
	}
	
	public static RegistrationView getInstance(){
		return RegistrationView.viewObject;
	}
	
	@PostConstruct
    public void init() {
        cities  = new HashMap<String, String>();
        cities.put("Ankara", "Ankara");
        cities.put("Istanbul", "Istanbul");
        cities.put("Izmir", "Izmir");
         
        Map<String,String> map = new HashMap<String, String>();
        map.put("Mamak", "Mamak");
        map.put("Çankaya", "Çankaya");
        map.put("Kecioren", "Kecioren");
        data.put("Ankara", map);
         
        map = new HashMap<String, String>();
        map.put("Adalar", "Adalar");
        map.put("Atasehir", "Atasehir");
        map.put("Avcilar", "Avcilar");
        data.put("Istanbul", map);
         
        map = new HashMap<String, String>();
        map.put("Buca", "Buca");
        map.put("Bornova", "Bornova");
        map.put("Cesme", "Cesme");
        data.put("Izmir", map);
    }
	
	
	
	public void addStudents(){
		Student student = new Student();
		student.setName(name);
		student.setCity(city);
		student.setDescription(description);
		student.setPhoneNumber(phoneNumber);
		student.setSurname(surName);
		student.setDistrict(district);
	    List<File> fileList = new ArrayList<File>();
		for(UploadedFile i : files.getFiles()){
			File file = new File();
			file.setFileData(i.getContent());
			file.setFileName(i.getFileName());
			file.setStudent(student);
			fileList.add(file);
		}
		student.setFiles(fileList);
		studentService.addStudent(student);
		listView.allStudents();
		clearStudent();
		
	}
	
	private void clearStudent() {
		name = null;
		surName = null;
        city = null;
        description = null;
        files = null;
        city = null;
        district = null;
        phoneNumber = null;
	}



	public void onCityChange() {
        if(city !=null && !city.equals(""))
            districts = data.get(city);
        else
            districts = new HashMap<String, String>();
    }
	
	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UploadedFiles getFiles() {
		return files;
	}

	public void setFiles(UploadedFiles files) {
		this.files = files;
	}

	public Map<String, String> getCities() {
		return cities;
	}

	public void setCities(Map<String, String> cities) {
		this.cities = cities;
	}

	public Map<String, String> getDistricts() {
		return districts;
	}

	public void setDistricts(Map<String, String> districts) {
		this.districts = districts;
	}
	
	protected Map getData(){
		return data;
	}

}
