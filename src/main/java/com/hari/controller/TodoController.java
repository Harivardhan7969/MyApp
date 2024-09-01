package com.hari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.dto.TodoDto;
import com.hari.entity.Todo;
import com.hari.service.TodoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
		TodoDto savedTodoDto = todoService.addTodo(todoDto);
		return new ResponseEntity<>(savedTodoDto,HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id){
		TodoDto todoDto = todoService.getTodo(id);
		return new ResponseEntity<>(todoDto,HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos(){
		List<TodoDto> allTodos = todoService.getAllTodos();
		return ResponseEntity.ok(allTodos);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,
			@PathVariable("id") Long id){
		TodoDto updatedTodoDto = todoService.updateTodo(todoDto, id);
		return ResponseEntity.ok(updatedTodoDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
		
		todoService.deleteTodo(id);
		return ResponseEntity.ok("Todo Deleted successfully ");
	}
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/completed")
	public ResponseEntity<TodoDto> completedTodo(@PathVariable("id") Long id){
		TodoDto todoDto = todoService.completedTodo(id);
		return ResponseEntity.ok(todoDto);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/in-completed")
	public ResponseEntity<TodoDto> inCompletedTodo(@PathVariable("id") Long id){
		TodoDto todoDto = todoService.inCompletedTodo(id);
		return ResponseEntity.ok(todoDto);
	}
	

}
