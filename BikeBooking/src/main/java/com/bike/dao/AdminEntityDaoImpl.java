package com.bike.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.bike.entities.User;

public class AdminEntityDaoImpl implements AdminEntityDao{

	@Override
	public List<User> getUserList() {
		return null;
	}
	
	
}
