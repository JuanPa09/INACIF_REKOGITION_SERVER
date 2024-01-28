package com.inacif.rekognition.web.app.repository;

import org.springframework.stereotype.Repository;

import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.projection.RequestApplicantDetail;
import com.inacif.rekognition.web.app.projection.RequestCitizenDetail;
import com.inacif.rekognition.web.app.projection.Requests;
import com.inacif.rekognition.web.app.projection.RequestsReport1;
import com.inacif.rekognition.web.app.projection.RequestsReport2;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	
	// REPORTS
	
	 @Query("SELECT r.id as id, r.applicantNames as applicantNames, r.applicantLastNames as applicantLastNames, r.applicantPhone as applicantPhone, r.applicantEmail as applicantEmail, "
			+ "r.attentionDate as attentionDate, rs.name as status FROM Request r "
			+ " JOIN RequestStatus rs ON r.statusId = rs.id "
			+ "WHERE r.statusId = (SELECT rs.id FROM RequestStatus rs WHERE rs.name = :condition) "
            + "AND r.createdDate BETWEEN :initDate AND :endDate")
    Optional<List<RequestsReport1>> getRequestsByDate(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("condition") String condition);
	 
	 @Query("SELECT r.id as id, r.applicantNames as applicantNames, r.applicantLastNames as applicantLastNames, r.applicantPhone as applicantPhone, r.applicantEmail as applicantEmail, "
	 		+ "r.attentionDate as attentionDate, rs.name as status FROM Request r "
			+ " JOIN RequestStatus rs ON r.statusId = rs.id"
	        + " WHERE r.createdDate BETWEEN :initDate AND :endDate")
	    Optional<List<RequestsReport1>> getAllRequestsByDate(@Param("initDate") String initDate, @Param("endDate") String endDate);
	 
	 @Query("SELECT DATE(r.createdDate) as createdDate, COUNT(*) as count FROM Request r"
	 		+ " WHERE r.createdDate BETWEEN :initDate AND :endDate"
	 		+ " AND r.statusId = (SELECT rs.id FROM RequestStatus rs WHERE rs.name = :condition)"
	 		+ " AND r.createdDate != NULL"
	 		+ " GROUP BY DATE(r.createdDate)") 
	 Optional<List<RequestsReport2>> getRequestsForDay(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("condition") String condition);
	 
	 @Query("SELECT DATE(r.createdDate) as createdDate, COUNT(*) as count FROM Request r"
		 		+ " WHERE r.createdDate BETWEEN :initDate AND :endDate"
		 		+ " AND r.createdDate != NULL"
		 		+ " GROUP BY DATE(r.createdDate)")  
		 Optional<List<RequestsReport2>> getAllRequestsForDay(@Param("initDate") String initDate, @Param("endDate") String endDate);

	
}
