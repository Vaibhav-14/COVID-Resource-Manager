package com.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dao.IPostFunctionDAO;

@Service("postService")
public class PostService {
	
	@Autowired
	private IPostFunctionDAO dao;

	public IPostFunctionDAO getDao() {
		return dao;
	}

	public void setDao(IPostFunctionDAO dao) {
		this.dao = dao;
	}
	

}
