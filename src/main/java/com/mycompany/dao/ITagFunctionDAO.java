package com.mycompany.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mycompany.entity.Tag;

public interface ITagFunctionDAO extends CrudRepository<Tag, String>{

	@Query(value = "select post_id from posts_tags where tag_name= :passedtag ", nativeQuery = true)
	List<Integer> getListOfAllPostswithTag(@Param("passedtag") String passedtag);
	
	@Query("SELECT name FROM Tag WHERE name LIKE %:keyword%")
	public List<String> searchTagsByKeyWord(@Param("keyword") String keyword);
	
}
