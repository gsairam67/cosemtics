<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add To Cart</title>
		<link href="style.css" rel="stylesheet" type="text/css" />
	</head>
 
	<body>
		<div class="content">
			<fieldset>
				<legend>Your Cart</legend>
				<!-- for triggering webflow events using links,
					 the eventId to be triggered is given in "href" attribute as:
				 -->
				<a href="${flowExecutionUrl}">Home</a>
				<div class="item">

      <img src="C:/Users/koel.chowdhury/DT TTT/Images/c_pizza_2.png" alt="pizza" class="img-responsive" height = "100" width="150">
    </div>
    
     
    <%-- <sf:form modelAttribute="userBean">
      <input name="submit" type="submit" value="Submit" />
     </sf:form><br>
      --%>
     
     <sf:form modelAttribute="userBean"><br />
					<sf:label path="qty">Quantity:</sf:label>
					<sf:input path="qty" /><br />
					<!-- to display validation messages -->
					<c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('qty')}" var="err"/>
					  <div><span>${err.text}</span></div>
					  <br>
					  <input name="_eventId_submit" type="submit" value="Submit" /><br />
   <br>    </sf:form><br>
			<%-- 
				<sf:form modelAttribute="userBean"><br />
					<sf:label path="userId">UserName:</sf:label>
					<sf:input path="userId" /><br />
					<!-- to display validation messages -->
					<c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('userId')}" var="err">
					  <div><span>${err.text}</span></div>
					</c:forEach><br />
					<sf:label path="email">Email Id: </sf:label>
					<sf:input path="email" /><br />
					<c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('email')}" var="err">
					  <div><span>${err.text}</span></div>
					</c:forEach><br />
					<sf:label path="age">Age: </sf:label>
					<sf:input path="age" /><br />
					<c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('age')}" var="err">
					  <div><span>${err.text}</span></div>
					</c:forEach><br />
					<!-- for triggering webflow events using form submission,
					 the eventId to be triggered is given in "name" attribute as:
					-->
					<input name="_eventId_submit" type="submit" value="Submit" /><br />
				</sf:form> --%>
			</fieldset>
		</div>
	</body>
</html>