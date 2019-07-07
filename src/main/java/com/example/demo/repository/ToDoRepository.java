package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, Long>{

	
	
}
