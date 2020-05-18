package net.as.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILE",schema="NETAS")
public class File {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FILE_ID")
	private int fileId;
	
	@Column(name="FILE_NAME")
	private String fileName;
	
	@Column(name="FILE_DATA", length=50000)
	private byte[] fileData;
	
	@ManyToOne
	@JoinColumn(name="STUDENT_ID")
	private Student student;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
