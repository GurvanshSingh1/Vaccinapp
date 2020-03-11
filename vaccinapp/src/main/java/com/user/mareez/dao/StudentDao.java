package com.user.mareez.dao;

import java.util.List;

import com.user.mareez.model.Course;
import com.user.mareez.model.Student;

 
public interface StudentDao {

	Student findByEmail(String email);
	
	int registerCourseByCourseCode(String email, String code);
	
	List<Course> findRegisteredCourses(String email);

}
