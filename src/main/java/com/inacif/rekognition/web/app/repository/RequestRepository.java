package com.inacif.rekognition.web.app.repository;

import org.springframework.stereotype.Repository;

import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.projection.RequestApplicantDetail;
import com.inacif.rekognition.web.app.projection.RequestCitizenDetail;
import com.inacif.rekognition.web.app.projection.Requests;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

	@Query(
			"SELECT r.id as id, r.applicantIdentificationNumber as applicantIdentificationNumber, r.applicantNames as applicantNames, r.applicantLastNames as applicantLastNames,"
			+ " r.createdDate as createdDate, r.attentionDate as attentionDate, rs.name as name"
			+ " FROM Request r"
			+ " JOIN RequestStatus rs ON r.statusId = rs.id"
			+ " WHERE r.statusId = :status"
			)
	Optional<List<Requests>> findByStatusId(Integer status);
			
	Request findByImage(String image);
	Optional<RequestApplicantDetail> findRequestApplicantDetailById(Long id);
	Optional<RequestCitizenDetail> findRequestCitizenDetailById(Long id);
	
	@Query(
	"SELECT r.id as id, r.applicantIdentificationNumber as applicantIdentificationNumber, r.applicantNames as applicantNames, r.applicantLastNames as applicantLastNames,"
	+ " r.createdDate as createdDate, r.attentionDate as attentionDate, rs.name as name"
	+ " FROM Request r"
	+ " JOIN RequestStatus rs ON r.statusId = rs.id"
			)
	Optional<List<Requests>> findAllRequestsBy();
	
}
