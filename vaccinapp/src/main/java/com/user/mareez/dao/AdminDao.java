package com.user.mareez.dao;

import java.util.List;

import com.user.mareez.model.AdminVaccinationInfo;
import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.Enquiry;
import com.user.mareez.model.User;

public interface AdminDao {

	
	List<User> findUnapprovedUsers();
	int approveUserByEmail(String userEmail);
	
	List<AdminVaccinationInfo> findAdminVaccination();
	int insertAdminUserVaccination(String vaccinType, String notes, int vaccinEffective);
	int deleteAdminVaccination(int vaccinId);
	
	
	int insertDailyNews(String news, String firstName, String date);
	
	List<Enquiry> findEnquiryByUser();
	int updateEnquiryReply(int enquiryId, String response);
	
	
	int insertNewClinic(String clinicName, String clinicAddress, String clinicContact, String clinicEmail);
	List<ClinicInfo> findClinics();
	int deleteClinic(int clinicId);
	

}
