package com.user.mareez.dao;

import com.user.mareez.model.User;

public interface UserDao {

	int insertNewUser(String fName, String lName, String email, String password, String gender, String dob, String address, String postal );
	
	User findByEmail(String email);

}
