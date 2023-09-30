package com.inacif.rekognition.web.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inacif.rekognition.web.app.entity.CaseInfo;
import com.inacif.rekognition.web.app.entity.ConfirmationCode;
import com.inacif.rekognition.web.app.entity.Functionality;
import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.entity.RequestStatus;
import com.inacif.rekognition.web.app.entity.Role;
import com.inacif.rekognition.web.app.entity.RoleFunctionality;
import com.inacif.rekognition.web.app.entity.SPMenu;
import com.inacif.rekognition.web.app.entity.Settings;
import com.inacif.rekognition.web.app.entity.Status;
import com.inacif.rekognition.web.app.entity.User;
import com.inacif.rekognition.web.app.projection.RequestApplicantDetail;
import com.inacif.rekognition.web.app.projection.RequestCitizenDetail;
import com.inacif.rekognition.web.app.projection.Requests;
import com.inacif.rekognition.web.app.projection.RolesFunctionalities;
import com.inacif.rekognition.web.app.repository.CaseRepository;
import com.inacif.rekognition.web.app.repository.ConfirmationCodeRepository;
import com.inacif.rekognition.web.app.repository.FunctionalityRepository;
import com.inacif.rekognition.web.app.repository.MenuRepository;
import com.inacif.rekognition.web.app.repository.RequestRepository;
import com.inacif.rekognition.web.app.repository.RequestStatusRepository;
import com.inacif.rekognition.web.app.repository.RoleFunctionalityRepository;
import com.inacif.rekognition.web.app.repository.RoleRepository;
import com.inacif.rekognition.web.app.repository.SettingsRepository;
import com.inacif.rekognition.web.app.repository.StatusRepository;
import com.inacif.rekognition.web.app.repository.UserRepository;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired S3ImageService s3ImageService;
	
	private final CaseRepository caseRepository;
	private final RequestStatusRepository requestStatusRepository;
	private final RequestRepository requestRepository;
	private final SettingsRepository settingsRepository;
	private final StatusRepository statusRepository;
	private final RoleRepository roleRepository;
	private final MenuRepository menuRepository;
	private final FunctionalityRepository functionalityRepository;
	private final RoleFunctionalityRepository roleFunctionalityRepository;
	private final UserRepository userRepository;
	private final ConfirmationCodeRepository confirmationCodeRepository;

	public QueryServiceImpl(
			CaseRepository caseRepository, 
			RequestStatusRepository requestStatusRepository,
			RequestRepository requestRepository,
			SettingsRepository settingsRepository,
			StatusRepository statusRepository,
			RoleRepository roleRepository,
			MenuRepository menuRepository,
			FunctionalityRepository functionalityRepository,
			RoleFunctionalityRepository roleFunctionalityRepository,
			UserRepository userRepository,
			ConfirmationCodeRepository confirmationCodeRepository) {
		
		this.caseRepository = caseRepository;
		this.requestStatusRepository = requestStatusRepository;
		this.requestRepository = requestRepository;
		this.settingsRepository = settingsRepository;
		this.statusRepository = statusRepository;
		this.roleRepository = roleRepository;
		this.menuRepository = menuRepository;
		this.functionalityRepository = functionalityRepository;
		this.roleFunctionalityRepository = roleFunctionalityRepository;
		this.userRepository = userRepository;
		this.confirmationCodeRepository = confirmationCodeRepository;
	}
	
	@Override
	public List<CaseInfo> getCasesInfoByImagesNames(List<String> names) {
		return caseRepository.findByImageIn(names);
	}

	@Override
	public Optional<RequestStatus> getRequestStatusIdByName(String name) {
		return requestStatusRepository.findByName(name);
	}

	@Override
	public Optional<List<Requests>> getRequestsByStatus(Integer status) {
		return requestRepository.findByStatusId(status);
	}

	@Override
	public Request getRequestByImage(String name) {
		return requestRepository.findByImage(name);
	}

	@Override
	public Request saveRequest(Request request) {
		String imageName = s3ImageService.upload(request.getImage(), "Request");
		request.setImage(imageName);
		return requestRepository.save(request);
	}
	
	@Override
	public Request updateRequest(Request request) {
		return requestRepository.save(request);
	}

	@Override
	public Optional<Request> getRequestById(Long id) {
		return requestRepository.findById(id);
	}

	@Override
	public Optional<RequestCitizenDetail> getRequestCitizenDetail(Long id) {
		return requestRepository.findRequestCitizenDetailById(id);
	}

	@Override
	public Optional<RequestApplicantDetail> getApplicantDetail(Long id) {
		return requestRepository.findRequestApplicantDetailById(id);
	}

	@Override
	public Optional<List<Requests>> getAllRequests() {
		return requestRepository.findAllRequestsBy();
	}
	
	@Override
	public Optional<RequestStatus> getRequestStatusById(Long id){
		return requestStatusRepository.findById(id);
	}
	
	@Override
	public List<Settings> getSettings() {
		return this.settingsRepository.findAll();
	}
	
	@Override
	public Settings saveSettings(Settings settings) {
		return this.settingsRepository.save(settings);
	}
	
	@Override
	public Optional<Status> getStatusByName(String name) {
		return this.statusRepository.findByName(name);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> getRoleById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	@Transactional
	public List<SPMenu> callSp_getMenuByRole(Integer param) {
		return menuRepository.callMenuProcedure(param);
	}

	@Override
	public List<Functionality> getAllFunctionalities() {
		return this.functionalityRepository.findAll();
	}

	@Override
	public List<RoleFunctionality> getRoleFunctionalityByRoleId(Long id) {
		return this.roleFunctionalityRepository.findByidRoleId(id);
	}

	@Override
	public List<RolesFunctionalities> getFunctionalitiesByRoleId(Long id) {
		return this.functionalityRepository.findByidRoleId(id);
	}
	
	@Override
	public RoleFunctionality saveRoleFunctionality(RoleFunctionality functionality) {
		return this.roleFunctionalityRepository.save(functionality);
	}
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> getUserByUsernameAndPassword(String username, String password) {
		return this.userRepository.findByUsernameAndPassword(username, password);
	}
	
	@Override
	public Optional<ConfirmationCode> getConfirmationCodeByCode(String code) {
		return this.confirmationCodeRepository.findByCode(code);
	}
	
	public ConfirmationCode saveConfirmationCode(ConfirmationCode confirmationCode) {
		return this.confirmationCodeRepository.save(confirmationCode);
	}
	
	
}
