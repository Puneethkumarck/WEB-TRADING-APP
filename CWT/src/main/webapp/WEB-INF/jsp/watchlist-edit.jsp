<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Watchlist:</h3>
	</div>
	
	<div class="panel-body">
	
		<h4 class="panel-title">Edit Watchlist</h4>
		
		<br/>
		
		<form:form action="${pageContext.request.contextPath}/admin/watchlist/edit" class="form" modelAttribute="watchlistForm" role="form">
			
			<div class="form-group">
				<form:label path="symbol">Symbol</form:label>
				<form:input path="symbol" class="form-control" />
 				<form:errors cssClass="error" path="symbol"></form:errors>
			</div>
			
			<div class="form-group">
				<form:label path="active">Active</form:label>
				<form:input path="active" class="form-control" />
 				<form:errors cssClass="error" path="active"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Save</button>
				<button type="reset" class="btn btn-default">Clear</button>
			</div>
			
		</form:form>
		
	</div>
	
</div>

<%@include file="include/footer.jsp" %>
