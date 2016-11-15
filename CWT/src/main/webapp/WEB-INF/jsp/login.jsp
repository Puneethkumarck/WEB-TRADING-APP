<%@include file="include/header.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Please login:</h3>
	</div>
	
	<div class="panel-body">
	
		<c:if test="${param.error != null}">
			<div class="alert alert-danger">
				Invalid username and/or password.
			</div>
		</c:if>
		
		<c:if test="${param.logout != null}">
			<div class="alert alert-danger">
				You've been logged out.
			</div>
		</c:if>
	
		<form:form role="form" method="post">
		
			<div class="form-group">
				<label for="username">Username</label>
				<input id="username" name="username" class="form-control" />
			</div>

			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" id="password" name="password" class="form-control" />
				<form:errors cssClass="error" path="password"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Login</button>
			</div>
		
		</form:form>
	</div>
</div>
	
<%@include file="include/footer.jsp" %>