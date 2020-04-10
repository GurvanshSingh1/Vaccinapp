package com.user.mareez.dao;

import java.util.List;

import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.DailyNews;
import com.user.mareez.model.Enquiry;
import com.user.mareez.model.User;
import com.user.mareez.model.UserVaccinationInfo;

public interface UserDao {

	int insertNewUser(String fName, String lName, String email, String password, String gender, String dob, String address, String postal );
	int insertNewUserVaccination(String fName, String vaccinationType, String vaccinationDate, String notes, int vaccinEffective);
	
	User findByEmail(String email);
	List<UserVaccinationInfo> findVaccinationByUser(String fName);
	int deleteUserVaccination(int vaccinId);
				
	List<DailyNews> findDailyNews();
	
	
	int insertEnquiry(String enquiry, String firstName, String email);
	List<Enquiry> findEnquiryByUser(String email);
		
	List<ClinicInfo> findClinics();
	
	List<ClinicInfo> findClinics(int clinicId);


}
