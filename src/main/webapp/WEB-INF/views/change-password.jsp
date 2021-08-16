<%@ include file="parts/meta.jsp" %> 
<title>Change Password</title>
<%@ include file="parts/header.jsp"%>
<c:if test="${isOldPasswordCorrect  == false}">
Check Old Password
<br>
<br>
<sf:form modelAttribute="user" method="POST" action = "/user/checkPassword">
Old Password : <sf:input path = "password"/>
<sf:errors path="password"></sf:errors>
<sf:hidden path="id"/>
<sf:hidden path="firstname"/>
<sf:hidden path="lastname"/>
<sf:hidden path="gender"/>
<sf:hidden path="username"/>
<sf:hidden path="email"/>
<sf:hidden path="mobile"/>
<sf:hidden path="warnings"/>
<sf:hidden path="dateOfBirth"/>

<br><br>
<sf:button name="submit" value="submit">Enter Password</sf:button>
</sf:form>
</c:if>
<c:if test="${isOldPasswordCorrect == true}">
Change Password
<br>
<br>
<sf:form modelAttribute="user" method="POST" action="/user/changePassword">
New Password : <sf:input path = "password"/>
<sf:errors path = "password"></sf:errors><br><br>
Confirm New Password : <sf:input path = "retypepassword"/>
<sf:errors path="retypepassword"></sf:errors>
<br><br>
<sf:hidden path="id"/>
<sf:hidden path="firstname"/>
<sf:hidden path="lastname"/>
<sf:hidden path="gender"/>
<sf:hidden path="username"/>
<sf:hidden path="email"/>
<sf:hidden path="mobile"/>
<sf:hidden path="warnings"/>
<sf:hidden path="dateOfBirth"/>
<sf:button name="submit" value="submit">Enter Password</sf:button>
</sf:form>
</c:if>
<%@ include file="parts/footer.jsp"%>