package com.hari.service;

import java.util.List;

import com.hari.dto.TodoDto;

public interface TodoService {
	
	TodoDto addTodo(TodoDto todoDto);
	
	TodoDto getTodo(Long id);
	
	List<TodoDto> getAllTodos();
	
    TodoDto	updateTodo(TodoDto todoDto,Long id);
    
    void deleteTodo(Long id);
    
    TodoDto completedTodo(Long id);
    
    TodoDto inCompletedTodo(Long id);

}
