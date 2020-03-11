package com.user.mareez.dao;

import java.util.List;

import com.user.mareez.model.Course;
 
public interface CourseDao {

	Course findByCode(String name);
	 
	List<Course> findAll();

}
