<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>

<body>
    <h1> Transfer money here </h1>
    <form action="transferMoney.mm">
        <label> Enter sender account Number </label>
        <input type="text" name="senderaccountnumber">
        <br>
        <br>
        <label> Enter receiver account Number </label>
        <input type="text" name="receiveraccountnumber">
        <br>
        <br>
        <label> Enter the amount </label>
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
