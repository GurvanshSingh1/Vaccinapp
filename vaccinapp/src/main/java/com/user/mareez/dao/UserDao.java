package com.user.mareez.dao;

import java.util.List;

import com.user.mareez.model.User;
import com.user.mareez.model.UserVaccinationInfo;

public interface UserDao {

	int insertNewUser(String fName, String lName, String email, String password, String gender, String dob, String address, String postal );
	int insertNewUserVaccination(String fName, String vaccinationType, String vaccinationDate, String notes);
	
	User findByEmail(String email);
	List<UserVaccinationInfo> findVaccinationByUser(String fName);

}
