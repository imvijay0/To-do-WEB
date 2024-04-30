package com.vj.springboot.firstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	 
	 
	private static List<Todo> todos = new ArrayList<>();
	
	 static int Todocount = 0;
	
	static {
		
		todos.add(new Todo(++Todocount,"itachi","learn spring",
				LocalDate.now().plusYears(1),false));
		
		todos.add(new Todo(++Todocount,"itachi","learn aws",
				LocalDate.now().plusYears(2),false));
		
		todos.add(new Todo(++Todocount,"itachi","learn cloud",
				LocalDate.now().plusYears(3),false));
		
		todos.add(new Todo(++Todocount,"itachi","learn java",
				LocalDate.now().plusYears(4),false));
		
		todos.add(new Todo(++Todocount,"itachi","learn dbms",
				LocalDate.now().plusYears(5),false));
		
		
	}
	
	public List<Todo> findByUsername(String username)
	{
		Predicate<? super Todo> predicate =
				todo -> todo.getUserName().equalsIgnoreCase(username);
		
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username,String description,
			LocalDate targetdate, boolean done)
	{
		Todo todo =  new Todo(++Todocount,username,description,targetdate,done);
		todos.add(todo);
	}
	
	public void deleteById(int id )
	{
		Predicate<? super Todo> predicate =
		todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		
		Predicate<? super Todo> predicate =
				todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public Todo updateTodo(@Valid Todo todo) {
		
		deleteById(todo.getId());
		
		todos.add(todo);
		
		return todo;
		
	}

	
}
