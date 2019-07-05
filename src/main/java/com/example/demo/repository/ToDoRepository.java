package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ToDo;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long>{

	
	
}
