<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>UPDATE ACCOUNT HERE </h1>
<form action="updateAccountDetails.mm">
	           	<label>Account Number : <br><input type="text" name="accNo" readonly="readonly" value="${requestScope.accounts.bankAccount.accountNumber}"></label><br><br></label> 
	           	<label>Name :<br><input type="text" name="accHolderName" value="${requestScope.accounts.bankAccount.accountHolderName}"/></label><br><br>
	           	<label>AccountBalance :<br><input type="text" name="accBalance" readonly="readonly" value="${requestScope.accounts.bankAccount.accountBalance}"></label><br><br>
				<label>Salaried :</label>
				<label><input type="radio" name="rdSal" ${requestScope.accounts.salary==true?"checked":""}>YES</label>
				<label><input type="radio" name="rdSal" ${requestScope.accounts.salary==true?"":"checked"}>NO</label><br><br>
				<label><input type="submit" name="submit" value="Submit"></label>
				<label><input type="reset" name="reset" value="Reset"></label>
   
        </form>
</body>
</html>
	           	
</body>
</html>