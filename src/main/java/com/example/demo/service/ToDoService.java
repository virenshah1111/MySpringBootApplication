package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ToDo;

public interface ToDoService {

	void save(ToDo toDo);

	List<ToDo> update(List<ToDo> toDoList, List<Long> idList);

	List<ToDo> getAll();

	List<ToDo> getByIds(List<Long> ids);

	void delete(List<Long> ids);

}
