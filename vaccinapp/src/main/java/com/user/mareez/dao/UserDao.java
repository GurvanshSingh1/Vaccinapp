package com.user.mareez.dao;

import java.util.List;

import com.user.mareez.model.AdminVaccinationInfo;
import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.DailyNews;
import com.user.mareez.model.Enquiry;
import com.user.mareez.model.User;
import com.user.mareez.model.UserVaccinationInfo;

public interface UserDao {

	int insertNewUser(String fName, String lName, String email, String password, String gender, String dob, String address, String postal );
	int insertNewUserVaccination(String fName, String vaccinationType, String vaccinationDate, String notes);
	
	User findByEmail(String email);
	List<UserVaccinationInfo> findVaccinationByUser(String fName);
	int deleteUserVaccination(int vaccinId);
	
	List<User> findUnapprovedUsers();
	int approveUserByEmail(String userEmail);
	
	List<AdminVaccinationInfo> findAdminVaccination();
	int insertAdminUserVaccination(String vaccinType, String notes, int vaccinEffective);
	int deleteAdminVaccination(int vaccinId);
	
	
	int insertDailyNews(String news, String firstName, String date);
	List<DailyNews> findDailyNews();
	
	
	int insertEnquiry(String enquiry, String firstName, String email);
	List<Enquiry> findEnquiryByUser(String email);
	List<Enquiry> findEnquiryByUser();
	int updateEnquiryReply(int enquiryId, String response);
	
	
	int insertNewClinic(String clinicName, String clinicAddress, String clinicContact, String clinicEmail);
	List<ClinicInfo> findClinics();
	int deleteClinic(int clinicId);
	List<ClinicInfo> findClinics(int clinicId);

}
