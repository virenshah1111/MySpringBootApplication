/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDoRepository;

/**
 * @author virens
 *
 */
@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoRepository toDoRepository;

	@Override
	public void save(ToDo toDo) {
		toDoRepository.save(toDo);
	}

	@Override
	public List<ToDo> update(List<ToDo> toDoList, List<Long> idList) {
		return (List<ToDo>) toDoRepository.saveAll(updateListFromActualData(toDoList, idList));
	}

	@Override
	public List<ToDo> getAll() {
		return (List<ToDo>) toDoRepository.findAll();
	}

	@Override
	public List<ToDo> getByIds(List<Long> ids) {
		return (List<ToDo>) toDoRepository.findAllById(ids);
	}

	@Override
	public void delete(List<Long> ids) {
		toDoRepository.deleteAll(toDoRepository.findAllById(ids));
	}

	private List<ToDo> updateListFromActualData(List<ToDo> toDoList, List<Long> idList) {
		List<ToDo> actalToDoList = getByIds(idList);
		int i = 0;
		for (ToDo toDo : actalToDoList) {
			ToDo toDoInput = toDoList.get(i++);
			toDo.setCompleted(toDoInput.isCompleted());
			toDo.setName(toDoInput.getName());
		}
		return actalToDoList;
	}

}
