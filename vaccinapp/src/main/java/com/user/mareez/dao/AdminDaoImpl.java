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

import com.user.mareez.model.AdminVaccinationInfo;
import com.user.mareez.model.ClinicInfo;
import com.user.mareez.model.DailyNews;
import com.user.mareez.model.Enquiry;
import com.user.mareez.model.User;

@Repository
public class AdminDaoImpl implements AdminDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}



	public List<User> findUnapprovedUsers() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isApproved", 0);

		String sql = "SELECT * FROM users WHERE isApproved=:isApproved";

		List<User> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper());

		// new BeanPropertyRowMapper(Customer.class));

		return result;
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


	public int approveUserByEmail(String userEmail) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", userEmail);

		String sql = "UPDATE users SET isApproved=1 WHERE email=:email ";
		return namedParameterJdbcTemplate.update(sql, params);
	}

	public List<AdminVaccinationInfo> findAdminVaccination() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("1", "1=1");
		String sql = "SELECT * FROM adminVaccination";

		List<AdminVaccinationInfo> result = namedParameterJdbcTemplate.query(sql, params, new AdminVaccinMapper());

		// new BeanPropertyRowMapper(Customer.class));

		return result;
	}
	
	private static final class AdminVaccinMapper implements RowMapper<AdminVaccinationInfo> {

		public AdminVaccinationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdminVaccinationInfo adminVaccinationInfo = new AdminVaccinationInfo();
			adminVaccinationInfo.setVaccinId(Integer.parseInt(rs.getString("vaccinId")));
			adminVaccinationInfo.setVaccinType(rs.getString("vaccinType"));
			adminVaccinationInfo.setNotes(rs.getString("notes"));
			adminVaccinationInfo.setVaccinEffective(Integer.parseInt(rs.getString("vaccinEffective")));
			return adminVaccinationInfo;
		}
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
	
	public int insertAdminUserVaccination(String vaccinType, String notes, int vaccinEffective) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vaccinType", vaccinType);
		params.put("notes", notes);
		params.put("vaccinEffective", vaccinEffective);
	

		String sql = "INSERT INTO adminVaccination (vaccinType, notes, vaccinEffective) VALUES (:vaccinType, :notes, :vaccinEffective)";
		return namedParameterJdbcTemplate.update(sql, params);
	}

	public int deleteAdminVaccination(int vaccinId) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("vaccinId", vaccinId);

		String sql = "DELETE FROM adminVaccination WHERE vaccinId=:vaccinId ";
		return namedParameterJdbcTemplate.update(sql, params);
	}

	public int insertDailyNews(String news, String firstName, String date) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("news", news);
		params.put("firstname", firstName);
		params.put("date", date);
	

		String sql = "INSERT INTO dailyNews (news, postedBy, postDate) VALUES (:news, :firstname, :date)";
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


	
	public List<Enquiry> findEnquiryByUser() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", "email");

		String sql = "SELECT * FROM enquiry";

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

	public int updateEnquiryReply(int enquiryId, String response) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enquiryId", enquiryId);
		params.put("response", response);
		params.put("IsReplied", 1);

		String sql = "UPDATE enquiry SET response=:response, IsReplied=:IsReplied WHERE enquiryId=:enquiryId ";
		return namedParameterJdbcTemplate.update(sql, params);
	}

	public int insertNewClinic(String clinicName, String clinicAddress, String clinicContact, String clinicEmail) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clinicName", clinicName);
		params.put("clinicAddress", clinicAddress);
		params.put("clinicContact", clinicContact);
		params.put("clinicEmail", clinicEmail);
	

		String sql = "INSERT INTO clinics (clinicName, clinicAddress, clinicContact, clinicEmail) VALUES (:clinicName, :clinicAddress, :clinicContact, :clinicEmail)";
		return namedParameterJdbcTemplate.update(sql, params);
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

	public int deleteClinic(int clinicId) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("clinicId", clinicId);

		String sql = "DELETE FROM clinics WHERE clinicId=:clinicId ";
		return namedParameterJdbcTemplate.update(sql, params);
	}


	

}
