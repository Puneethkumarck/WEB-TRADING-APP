<%@include file="include/header.jsp" %>

<div class="panel panel-primary">

	<div class="panel-heading">
		<h3 class="panel-title">Forgot Password:</h3>
	</div>
	
	<div class="panel-body">
		<form:form class="form" modelAttribute="username" method="post">
		
			<div class="form-group">
				<form:label path="username">Username:</form:label>
				<form:input path="username" class="form-control" placeholder="Your username" />
 				<form:errors cssClass="error" path="username"></form:errors>
			</div>

			<div>
				<button type="submit" class="btn btn-primary">Yes, send me the password</button>
			</div>
		
		</form:form>
	</div>

</div>

<%@include file="include/footer.jsp" %>