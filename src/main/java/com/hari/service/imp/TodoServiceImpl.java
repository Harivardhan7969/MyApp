package com.hari.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hari.dto.TodoDto;
import com.hari.entity.Todo;
import com.hari.exception.ResourceNotFoundException;
import com.hari.repo.TodoRepository;
import com.hari.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
//		Todo todo = new Todo();
//		todo.setId(todoDto.getId());
//		todo.setTitle(todoDto.getTitle());
//		todo.setDescription(todoDto.getDescription());
//		todo.setCompleted(todoDto.isCompleted());
		//converting dto class to Todo entity class
		Todo todo = modelMapper.map(todoDto, Todo.class);
		
		Todo saveTodo = todoRepository.save(todo);
		//converting entity Todo class to dto class
		TodoDto savedTodoDto = modelMapper.map(saveTodo, TodoDto.class);
		
//		todoDto.setId(saveTodo.getId());
//		todoDto.setTitle(saveTodo.getTitle());
//		todoDto.setDescription(saveTodo.getDescription());
//		todoDto.setCompleted(saveTodo.isCompleted());
		
		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Todo not found with given id "+ id)
				);
		TodoDto todoDto = modelMapper.map(todo, TodoDto.class);
		return todoDto;
	}

	@Override	
	public List<TodoDto> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream().map((todo)-> modelMapper.map(todo, TodoDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Todo not found with given id "+id));
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		Todo savedTodo = todoRepository.save(todo);
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		return savedTodoDto;
	}

	@Override
	public void deleteTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Todo not found with given Id "+id)
				);
		todoRepository.deleteById(id);
	}

	@Override
	public TodoDto completedTodo(Long id) {
		 Todo todo = todoRepository.findById(id).orElseThrow(
				 ()-> new ResourceNotFoundException("Todo not found with given Id"+id)
				 );
		 
		 todo.setCompleted(Boolean.TRUE);
		 Todo saveTodo = todoRepository.save(todo);
		 TodoDto todoDto = modelMapper.map(saveTodo, TodoDto.class);
		return todoDto;
	}

	@Override
	public TodoDto inCompletedTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(
				 ()-> new ResourceNotFoundException("Todo not found with given Id"+id)
				 );
		 
		 todo.setCompleted(Boolean.FALSE);
		 Todo savedTodo = todoRepository.save(todo);
		 TodoDto todoDto = modelMapper.map(savedTodo, TodoDto.class);
		return todoDto;
	}

}
