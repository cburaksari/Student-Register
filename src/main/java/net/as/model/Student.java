package net.as.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="STUDENT",schema="NETAS")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id")
	private int id;
	
	@Size(min=3, message="Your name should be at least 3 characters.")
	@Column(name="student_name", nullable=false) @NotEmpty(message="Name cannot be empty!")
	private String name;
	
	@Column(name="student_surname", nullable=false)@NotEmpty(message="Surname cannot be empty!")
	private String surname;
	
	@Column(name="phone_number", nullable=false)@NotEmpty(message="Phone-number cannot be empty!")
	private String phoneNumber;
	
	@Column(name="city",nullable=false)@NotEmpty(message="City cannot be empty!")
	private String city;
	
	@Column(name="district",nullable=false)@NotEmpty(message="District cannot be empty!")
	private String district;
	
	@Column(name="description",nullable=false)@NotEmpty(message="Description cannot be empty!")
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="student", cascade=CascadeType.ALL)
	private List<File> files;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

}
