<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

    <h1> Welcome to Withdraw page </h1>
    <form action="withdrawBalance.mm">
        <label> Enter your account Number </label>
        <input type="text" name="accountnumber">
        <br>
        <br>
        <label> Enter the amount to withdraw </label>
        <input type="number" name="amount">
        <br>
        <br>
        <input type="submit" name="submit" value="submit">
    </form>
    <br>
        <div>
		<jsp:include page="homeLink.html"></jsp:include>
	</div>
</body>

</html>