package com.inacif.rekognition.web.app.entity;

import java.sql.Date;

import com.inacif.rekognition.web.app.persistance.RequestEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "Request")
@EntityListeners(RequestEntityListener.class)
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String attentionDate;
    
    private String attentionUser;
    
    @JoinColumn(name = "status_id")
    private Long statusId;
    
    private String applicantNationality;
    private String applicantIdentificationType;
    private String applicantIdentificationNumber;
    @Temporal(TemporalType.DATE)
    private String applicantBirthdate;
    
    private String applicantNames;
    private String applicantLastNames;
    private String applicantSex;
    private int applicantPhone;
    private String applicantEmail;
    private String disappearedFirstName;
    private String disappearedSecondName;
    private String disappearedOtherNames;
    private String disappearedLastName;
    private String disappearedSecondLastName;
    
    @Temporal(TemporalType.DATE)
    private String disappearedBirthdate;
    
    private int disappearedHeight;
    private String disappearedMunicipality;
    private String disappearedComplexion;
    private String disappearedEyesColor;
    private String disappearedHairColor;
    private int disappearedFeetSize;
    private String disappearedSkin;
    private String disappearedSex;
    private String image;
    private String additionalDescription;
    
    @Temporal(TemporalType.TIMESTAMP)
    private String createdDate = null;
    
    @ManyToOne
    @JoinColumn(name = "caseId")
    private CaseInfo caseId;

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttentionDate() {
		return attentionDate;
	}

	public void setAttentionDate(String attentionDate) {
		this.attentionDate = attentionDate;
	}

	public String getAttentionUser() {
		return attentionUser;
	}

	public void setAttentionUser(String attentionUser) {
		this.attentionUser = attentionUser;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatus(Long status) {
		this.statusId = status;
	}

	public String getApplicantNationality() {
		return applicantNationality;
	}

	public void setApplicantNationality(String applicantNationality) {
		this.applicantNationality = applicantNationality;
	}

	public String getApplicantIdentificationType() {
		return applicantIdentificationType;
	}

	public void setApplicantIdentificationType(String applicantIdentificationType) {
		this.applicantIdentificationType = applicantIdentificationType;
	}

	public String getApplicantIdentificationNumber() {
		return applicantIdentificationNumber;
	}

	public void setApplicantIdentificationNumber(String applicantIdentificationNumber) {
		this.applicantIdentificationNumber = applicantIdentificationNumber;
	}

	public String getApplicantBirthdate() {
		return applicantBirthdate;
	}

	public void setApplicantBirthdate(String applicantBirthdate) {
		this.applicantBirthdate = applicantBirthdate;
	}

	public String getApplicantNames() {
		return applicantNames;
	}

	public void setApplicantNames(String applicantNames) {
		this.applicantNames = applicantNames;
	}

	public String getApplicantLastNames() {
		return applicantLastNames;
	}

	public void setApplicantLastNames(String applicantLastNames) {
		this.applicantLastNames = applicantLastNames;
	}

	public String getApplicantSex() {
		return applicantSex;
	}

	public void setApplicantSex(String applicantSex) {
		this.applicantSex = applicantSex;
	}

	public int getApplicantPhone() {
		return applicantPhone;
	}

	public void setApplicantPhone(int applicantPhone) {
		this.applicantPhone = applicantPhone;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

	public String getDisappearedFirstName() {
		return disappearedFirstName;
	}

	public void setDisappearedFirstName(String disappearedFirstName) {
		this.disappearedFirstName = disappearedFirstName;
	}

	public String getDisappearedSecondName() {
		return disappearedSecondName;
	}

	public void setDisappearedSecondName(String disappearedSecondName) {
		this.disappearedSecondName = disappearedSecondName;
	}

	public String getDisappearedOtherNames() {
		return disappearedOtherNames;
	}

	public void setDisappearedOtherNames(String disappearedOtherNames) {
		this.disappearedOtherNames = disappearedOtherNames;
	}

	public String getDisappearedLastName() {
		return disappearedLastName;
	}

	public void setDisappearedLastName(String disappearedLastName) {
		this.disappearedLastName = disappearedLastName;
	}

	public String getDisappearedSecondLastName() {
		return disappearedSecondLastName;
	}

	public void setDisappearedSecondLastName(String disappearedSecondLastName) {
		this.disappearedSecondLastName = disappearedSecondLastName;
	}

	public String getDisappearedBirthdate() {
		return disappearedBirthdate;
	}

	public void setDisappearedBirthdate(String disappearedBirthdate) {
		this.disappearedBirthdate = disappearedBirthdate;
	}

	public int getDisappearedHeight() {
		return disappearedHeight;
	}

	public void setDisappearedHeight(int disappearedHeight) {
		this.disappearedHeight = disappearedHeight;
	}

	public String getDisappearedMunicipality() {
		return disappearedMunicipality;
	}

	public void setDisappearedMunicipality(String disappearedMunicipality) {
		this.disappearedMunicipality = disappearedMunicipality;
	}

	public String getDisappearedComplexion() {
		return disappearedComplexion;
	}

	public void setDisappearedComplexion(String disappearedComplexion) {
		this.disappearedComplexion = disappearedComplexion;
	}

	public String getDisappearedEyesColor() {
		return disappearedEyesColor;
	}

	public void setDisappearedEyesColor(String disappearedEyesColor) {
		this.disappearedEyesColor = disappearedEyesColor;
	}

	public String getDisappearedHairColor() {
		return disappearedHairColor;
	}

	public void setDisappearedHairColor(String disappearedHairColor) {
		this.disappearedHairColor = disappearedHairColor;
	}

	public int getDisappearedFeetSize() {
		return disappearedFeetSize;
	}

	public void setDisappearedFeetSize(int disappearedFeetSize) {
		this.disappearedFeetSize = disappearedFeetSize;
	}

	public String getDisappearedSkin() {
		return disappearedSkin;
	}

	public void setDisappearedSkin(String disappearedSkin) {
		this.disappearedSkin = disappearedSkin;
	}

	public String getDisappearedSex() {
		return disappearedSex;
	}

	public void setDisappearedSex(String disappearedSex) {
		this.disappearedSex = disappearedSex;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAdditionalDescription() {
		return additionalDescription;
	}

	public void setAdditionalDescription(String additionalDescription) {
		this.additionalDescription = additionalDescription;
	}

	public CaseInfo getCaseId() {
		return caseId;
	}

	public void setCaseId(CaseInfo caseId) {
		this.caseId = caseId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
}