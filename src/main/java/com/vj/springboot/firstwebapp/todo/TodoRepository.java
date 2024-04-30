package com.vj.springboot.firstwebapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer>{

	public List<Todo> findByUsername(String userName);

	
}
