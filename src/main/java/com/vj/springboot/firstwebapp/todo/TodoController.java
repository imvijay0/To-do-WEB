package com.vj.springboot.firstwebapp.todo;
import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
	
	
	private TodoService todoService;

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	
	@RequestMapping("/list-todos")
	public String listTodos(ModelMap model)
	{
		String username = getLoggedInUsername( model);
		
	List<Todo> todos = todoService.findByUsername(username);	
	model.addAttribute("todos", todos);
		return "alltodolists";
	}
	
	@RequestMapping(value = "/add-todo" , method = RequestMethod.GET)
	public String ShowTodos(ModelMap model)
	{
		String username =(String)  model.get("name");
		Todo todo = new Todo(0,username,"",LocalDate.now().plusYears(1),false);
		model.put("todo", todo);
		
		
		
		return "add-todo";
	}
	
	@RequestMapping(value = "/add-todo" , method = RequestMethod.POST)
	public String AddTodos(ModelMap model,@Valid Todo todo,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "add-todo";
		}
		
		
		String username =(String)  model.get("name");
		
		todoService.addTodo(username, todo.getDescription(), todo.getTargetdate(), false);
		return "redirect:list-todos";
	}
	
	
	@RequestMapping(value = "/delete-todo" , method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id)
	{
		todoService.deleteById(id);
		
		return "redirect:list-todos";
	}
	
	@RequestMapping(value = "/update-todo" , method = RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id,ModelMap model)
	{
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		
		return "add-todo";
	}
	
	@RequestMapping(value = "/update-todo" , method = RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "add-todo";
		}
		
		
		String username =(String)  model.get("name");
		todo.setUserName(username);
		todoService.updateTodo(todo);
		return "redirect:list-todos"; 
	}
	
	private String getLoggedInUsername(ModelMap model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	
	
}
