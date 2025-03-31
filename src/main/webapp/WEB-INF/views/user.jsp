<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>
<h1>Welcome, ${user.name}</h1>
<p>Login: ${user.login}</p>
<p>Role: ${user.role}</p>

<h2>Your Cards</h2>
<c:forEach var="card" items="${cards}">
    <p>Card Number: ${card.cardNumber}</p>
</c:forEach>

<h2>Your Accounts</h2>
<c:forEach var="account" items="${accounts}">
    <p>Account ID: ${account.id}, Balance: ${account.balance}</p>
    <form action="/block-account" method="post">
        <input type="hidden" name="accountId" value="${account.id}">
        <button type="submit">Block Account</button>
    </form>
</c:forEach>

<h2>Payment History</h2>
<c:forEach var="payment" items="${payments}">
    <p>Amount: ${payment.amount}, PaymentId: ${payment.id}</p>
</c:forEach>

<h2>Make a Payment</h2>
<form action="/make-payment" method="post">
    <label>Transfer money from Account ID: <input type="number" name="accountIdToPay"></label>
    <label>Transfer money to Account ID: <input type="number" name="accountIdToGet"></label>
    <label>Amount: <input type="number" name="amount" step="0.01"></label>
    <button type="submit">Pay</button>
</form>

<h2>Deposit Money</h2>
<form action="/deposit-money" method="post">
    <label>Account ID: <input type="number" name="accountId"></label>
    <label>Amount: <input type="number" name="amount" step="0.01"></label>
    <button type="submit">Deposit</button>
</form>
</body>
</html>