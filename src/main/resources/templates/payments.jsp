<!-- payments.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Payments for ${account.id}</h1>
<c:forEach items="${payments}" var="payment">
    <p>${payment.amount} - ${payment.timestamp}</p>
</c:forEach>
</body>
</html>