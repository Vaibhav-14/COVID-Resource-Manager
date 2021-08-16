<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
    <h2>Vaccination Slots</h2>

    <c:if test="${not empty vaccines}">

        <ul>
            <c:forEach var="sessions" items="${vaccines}">
                <li>${sessions}</li>
            </c:forEach>
        </ul>

    </c:if>
</body>
</html>