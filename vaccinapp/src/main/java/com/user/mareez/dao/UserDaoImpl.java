package com.user.mareez.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.user.mareez.model.User;
import com.user.mareez.model.UserVaccinationInfo;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public int insertNewUser(String fName, String lName, String email, String password, String gender, String dob,
			String address, String postal) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fName", fName);
		params.put("lName", lName);
		params.put("email", email);
		params.put("password", password);
		params.put("gender", gender);
		params.put("dob", dob);
		params.put("address", address);
		params.put("postal", postal);

		String sql = "INSERT INTO users VALUES (1, :fName, :lName, 'USER', :gender, :email, :password, :address, :postal, :dob, 0)";
		return namedParameterJdbcTemplate.update(sql, params);
	}

	public User findByEmail(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);

		String sql = "SELECT * FROM users WHERE email=:email";

//        Student result = namedParameterJdbcTemplate.queryForObject(
//                    sql,
//                    params,
//                    new StudentMapper());

		List<User> results = namedParameterJdbcTemplate.query(sql, params, new UserMapper());

		if(results.size() == 0) {
			return null;
		}
		User user = results.get(0);
						
		return user;
	}
	
	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setFirstName(rs.getString("fname"));
			user.setLastName(rs.getString("lname"));
			user.setGender(rs.getString("gender"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setIsApproved(rs.getInt("isApproved"));
			user.setUserType(rs.getString("userType"));
			return user;
		}
	}

	public int insertNewUserVaccination(String fName, String vaccinationType, String vaccinationDate, String notes) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fName", fName);
		params.put("vaccinationType", vaccinationType);
		params.put("vaccinationDate", vaccinationDate);
		params.put("notes", notes);


		String sql = "INSERT INTO userVaccination VALUES (1, :fName, :vaccinationType, :notes, :vaccinationDate)";
		return namedParameterJdbcTemplate.update(sql, params);
	}

	public List<UserVaccinationInfo> findVaccinationByUser(String fName) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("fName", fName);
        
		String sql = "SELECT * FROM userVaccination WHERE fname=:fName";
		
		List<UserVaccinationInfo> result = namedParameterJdbcTemplate.query(sql, params, new VaccinationMapper());
                    
        //new BeanPropertyRowMapper(Customer.class));
        
        return result;
	}
	
	private static final class VaccinationMapper implements RowMapper<UserVaccinationInfo> {

		public UserVaccinationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVaccinationInfo userVaccinationInfo = new UserVaccinationInfo();
			userVaccinationInfo.setVaccinDate(rs.getString("vaccinDate"));
			userVaccinationInfo.setVaccinType(rs.getString("vaccinType"));
			userVaccinationInfo.setNotes(rs.getString("notes"));
			return userVaccinationInfo;
		}
	}

	public int deleteUserVaccination(String firstName, String vaccinType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fName", firstName);
		params.put("vaccinType", vaccinType);
	

		String sql = "DELETE FROM userVaccination WHERE fname=:fName AND vaccinType=:vaccinType ";
		return namedParameterJdbcTemplate.update(sql, params);
		
	}

	public List<User> findUnapprovedUsers() {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("isApproved", 0);
        
		String sql = "SELECT * FROM users WHERE isApproved=:isApproved";
		
		List<User> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper());
                    
        //new BeanPropertyRowMapper(Customer.class));
        
        return result;
	}

	public int approveUserByEmail(String userEmail) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", userEmail);
	
		String sql = "UPDATE users SET isApproved=1 WHERE email=:email ";
		return namedParameterJdbcTemplate.update(sql, params);
	}

}
