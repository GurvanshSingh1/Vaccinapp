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

import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.DailyNews;
import com.user.mareez.model.Enquiry;
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

		if (results.size() == 0) {
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

	public int insertNewUserVaccination(String fName, String vaccinationType, String vaccinationDate, String notes, int vaccinEffective) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fName", fName);
		params.put("vaccinationType", vaccinationType);
		params.put("vaccinationDate", vaccinationDate);
		params.put("notes", notes);
		params.put("vaccinEffective", vaccinEffective);

		String sql = "INSERT INTO userVaccination(fname, vaccinType, notes, vaccinDate, vaccinEffective) VALUES (:fName, :vaccinationType, :notes, :vaccinationDate, :vaccinEffective)";
		return namedParameterJdbcTemplate.update(sql, params);
	}

	public List<UserVaccinationInfo> findVaccinationByUser(String fName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fName", fName);

		String sql = "SELECT *, DATEDIFF(DAY, CURDATE(), DATEADD(MONTH, vaccinEffective, CURDATE())) as vaccinDue  FROM userVaccination WHERE fname=:fName";

		List<UserVaccinationInfo> result = namedParameterJdbcTemplate.query(sql, params, new VaccinationMapper());

		// new BeanPropertyRowMapper(Customer.class));

		return result;
	}

	private static final class VaccinationMapper implements RowMapper<UserVaccinationInfo> {

		public UserVaccinationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVaccinationInfo userVaccinationInfo = new UserVaccinationInfo();
			userVaccinationInfo.setVaccinId((Integer.parseInt(rs.getString("vaccinId"))));
			userVaccinationInfo.setVaccinDate(rs.getString("vaccinDate"));
			userVaccinationInfo.setVaccinType(rs.getString("vaccinType"));
			userVaccinationInfo.setNotes(rs.getString("notes"));
			userVaccinationInfo.setVaccinEffective(Integer.parseInt(rs.getString("vaccinEffective")));
			userVaccinationInfo.setVaccinDue(rs.getString("vaccinDue"));
			return userVaccinationInfo;
		}
	}

	public int deleteUserVaccination(int vaccinId) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("vaccinId", vaccinId);

		String sql = "DELETE FROM userVaccination WHERE vaccinId=:vaccinId ";
		return namedParameterJdbcTemplate.update(sql, params);

	}



	public List<DailyNews> findDailyNews() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("1", "1=1");
		String sql = "SELECT * FROM dailyNews order by newsId desc";

		List<DailyNews> result = namedParameterJdbcTemplate.query(sql, params, new DailyNewsMapper());

		// new BeanPropertyRowMapper(Customer.class));

		return result;
	}
	private static final class DailyNewsMapper implements RowMapper<DailyNews> {

		public DailyNews mapRow(ResultSet rs, int rowNum) throws SQLException {
			DailyNews dailyNews = new DailyNews();
			dailyNews.setNewsId(Integer.parseInt(rs.getString("newsId")));
			dailyNews.setNews(rs.getString("news"));
			dailyNews.setPostedBy(rs.getString("postedBy"));
			dailyNews.setPostdate(rs.getString("postDate"));
			
			return dailyNews;
		}
	}

	public int insertEnquiry(String enquiry, String firstName, String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enquiry", enquiry);
		params.put("firstname", firstName);
		params.put("email", email);
	

		String sql = "INSERT INTO enquiry(enquiry, name, email ,response, isReplied) VALUES (:enquiry, :firstname, :email, 'Waiting Response!', 0)";
		return namedParameterJdbcTemplate.update(sql, params);
	}

	public List<Enquiry> findEnquiryByUser(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);

		String sql = "SELECT * FROM enquiry WHERE email=:email";

		List<Enquiry> result = namedParameterJdbcTemplate.query(sql, params, new EnquiryMapper());

		// new BeanPropertyRowMapper(Customer.class));

		return result;
	}
	
	
	private static final class EnquiryMapper implements RowMapper<Enquiry> {

		public Enquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
			Enquiry enquiry = new Enquiry();
			enquiry.setEnquiryId(Integer.parseInt(rs.getString("enquiryId")));
			enquiry.setEnquiry(rs.getString("enquiry"));
			enquiry.setName(rs.getString("name"));
			enquiry.setEmail(rs.getString("email"));
			enquiry.setResponse(rs.getString("response"));
			enquiry.setIsReplied(Integer.parseInt(rs.getString("isReplied")));
			
			
			return enquiry;
		}
	}


	public List<ClinicInfo> findClinics() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", "email");

		String sql = "SELECT * FROM clinics";

		List<ClinicInfo> result = namedParameterJdbcTemplate.query(sql, params, new ClinicMapper());

		// new BeanPropertyRowMapper(Customer.class));

		return result;
	}
	
	private static final class ClinicMapper implements RowMapper<ClinicInfo> {

		public ClinicInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ClinicInfo clinicInfo = new ClinicInfo();
			clinicInfo.setClinicId(Integer.parseInt(rs.getString("clinicId")));
			clinicInfo.setClinicName(rs.getString("clinicName"));
			clinicInfo.setClinicAddress(rs.getString("clinicAddress"));
			clinicInfo.setClinicEmail(rs.getString("clinicEmail"));
			clinicInfo.setClinicContact(rs.getString("clinicContact"));
			
			
			return clinicInfo;
		}
	}


	public List<ClinicInfo> findClinics(int clinicId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clinicId", clinicId);

		String sql = "SELECT * FROM clinics WHERE clinicId = :clinicId";

		List<ClinicInfo> result = namedParameterJdbcTemplate.query(sql, params, new ClinicMapper());

		// new BeanPropertyRowMapper(Customer.class));

		return result;
	}
	
	

}
