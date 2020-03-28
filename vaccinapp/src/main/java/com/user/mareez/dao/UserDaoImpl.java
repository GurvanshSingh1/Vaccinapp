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

		String sql = "INSERT INTO users VALUES (1, :fName, :lName, :gender, :email, :password, :address, :postal, :dob)";
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
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
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

}
