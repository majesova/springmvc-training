package com.plenumsoft.vuzee.entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="candidates")
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String positionApplied;
	private Date createdAt;
	private String createdBy;
	@Column(name="cv_file")
	private byte[] cvFile;
	@Column(name="cv_mime_type")
	private String cvMimeType;
	@Column(name="cv_extension")
	private String cvExtension;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPositionApplied() {
		return positionApplied;
	}
	public void setPositionApplied(String positionApplied) {
		this.positionApplied = positionApplied;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public byte[] getCvFile() {
		return cvFile;
	}
	public void setCvFile(byte[] cvFile) {
		this.cvFile = cvFile;
	}
	public String getCvMimeType() {
		return cvMimeType;
	}
	public void setCvMimeType(String cvMimeType) {
		this.cvMimeType = cvMimeType;
	}
	public String getCvExtension() {
		return cvExtension;
	}
	public void setCvExtension(String cvExtension) {
		this.cvExtension = cvExtension;
	}
}