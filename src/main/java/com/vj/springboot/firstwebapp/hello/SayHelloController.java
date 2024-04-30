package com.vj.springboot.firstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
 
	
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello()
	{
		return "yo,dude what the heck";
	}
	
	//   say-hello-jsp => redirected to sayHello.jsp file.
	//  /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp()
	{
		return "sayHello";
	}
	
	
	
	
	
}
