<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>

<body>
	<h2>Customer SignUp Form</h2>

	<form:form method="POST" commandName="customer" action="customer/login">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table>
			<tr>
				<td>Customer Name :</td>
				<td><form:input path="custname" /></td>
				<td><form:errors path="custname" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Customer Email :</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Customer Address :</td>
				<td><form:input path="address" /></td>
				<td><form:errors path="address" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td>Customer Password :</td>
				<td><form:input path="password"  type="password"/></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Customer Re-Password :</td>
				<td><form:input path="repassword"  type="password"/></td>
				<td><form:errors path="repassword" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td colspan="3"><input type="submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>