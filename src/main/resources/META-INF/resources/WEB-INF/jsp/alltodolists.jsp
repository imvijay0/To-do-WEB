
		
		<%@ include file="common/header.jspf" %>
		<%@ include file="common/navigation.jspf" %>
	
	
	<div class = "container">
		
		<h1>Here are you todos</h1> 
		
		<table class = "table">
			<thead>
				<tr>
					
					<th>Description </th>
					<th> targetDate</th>
					<th>is Done?</th>
					<th> </th>
					<th> <th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items = "${todos}" var = "todo">
				<tr>
					
					<td>${todo.description} </td>
					<td>${todo.targetdate} </td>
					<td>${todo.done} </td>
					<td> <a href = "update-todo?id=${todo.id}" class = "btn btn-warning">
					UPDATE </a>  </td>
					<td> <a href= "delete-todo?id= ${todo.id}" 
					class = "btn btn-success"> DELETE   </td>
					
					
				</tr>
				
				
				</c:forEach>
				
			
			</tbody>
		
		</table>
		
		
		<a href = "add-todo" class = "btn btn-success"> AddTodo</a>
		
		</div>
		
		<%@ include file="common/footer.jspf"%>
