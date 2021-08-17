<%@ include file="parts/meta.jsp" %> 
<title>Change Password</title>
<%@ include file="parts/header.jsp"%>



<c:if test="${isOldPasswordCorrect  == false}">
<h1 class="text-center">Reset Password</h1>
<br>
<br>

<div
	class="formcontainer w-100 h-100 p-5 d-flex justify-content-center vh-200">
	<div class="container w-50 p-3 text-center bg-secondary rounded shadow-lg ">
<sf:form modelAttribute="user" method="POST" action = "/user/checkPassword">

<%-- Old Password : <sf:input path = "password"/> --%>



<label for="inputPassword5" class="form-label text-light"><h3>Password</h3></label>
<sf:input type="password" id="inputPassword5" class="form-control" aria-describedby="passwordHelpBlock" path = "password"/>


<div id="validationDefaultUsername" class="form-text text-light">
Kindly enter your old password to continue
</div>



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
<sf:button class="btn btn-primary" type="submit" name="submit" value="submit">Continue</sf:button>
</sf:form>
</div>
</div>
</c:if>
<c:if test="${isOldPasswordCorrect == true}">
<h1 class="text-center">Change Password</h1>
<br>
<br>
<div
	class="formcontainer w-100 h-100 p-5 d-flex justify-content-center vh-200">
<div class="conatiner w-50 p-3 text-center bg-secondary" style = "horizontal-align: middle;margin-left: auto;
    margin-right: auto;">
<sf:form modelAttribute="user" method="POST" action="/user/changePassword">

<%-- New Password : <sf:input path = "password"/>--%>


<div class="form-group row">
    <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg text-dark">New Password</label>
    <div class="col-sm-10">
      <sf:input type="password" class="form-control form-control-lg" id="colFormLabelLg" placeholder="Enter new password" path = "password"/>
    </div>
</div>

<sf:errors path = "password"></sf:errors><br><br>
<%--Confirm New Password : <sf:input path = "retypepassword"/>--%>
<div class="form-group row">
    <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg text-dark">Retype Password</label>
    <div class="col-sm-10">
      <sf:input type="password" class="input form-control form-control-lg" id="password" placeholder="Retype password" path = "retypepassword"/>
	</div>
</div>

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
<sf:button class="btn btn-primary" type="submit" name="submit" value="submit">Change Password</sf:button>
</sf:form>
</div>
</div>
</c:if>
<%@ include file="parts/footer.jsp"%>