<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c"  uri ="http://java.sun.com/jsp/jstl/core %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <h1>  HTTP Status 403 -Access is defined</h1>
  <c:choosen>
     <c:when test="${empty username}"></c:when>
      <c:otherwise>
               <h2>Username : ${user name }<br>     
                         you do not have permission to access this page!</h2>
     </c:otherwise>
   </c:choosen>
   
   </body>
   </html>