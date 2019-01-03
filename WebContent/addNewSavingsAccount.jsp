<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addnewSavingsAccount.mm">
<label> Account Holder Name </label>
<input type="text" name="account_hn"> <br> <br>
<label> Account type :</label> yes
<input type="radio" name="salary"> no 
<input type="radio" name="salary"> <br>
<label> Account Balance : </label>
<input type="number" name="accountbalance"><br> <br>
<input type="submit" name="submit" value="Submit">
</form>
<br>
<div>
		<jsp:include page="homeLink.html"></jsp:include>
	</div>
</body>
</html>