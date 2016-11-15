<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Chart:</h3>
	</div>
	
	<div class="panel-body">
	
		<br/>
		
		<form:form action="${pageContext.request.contextPath}/member/chart" class="form" modelAttribute="chart" role="form">
			
			<div class="form-group">
				<form:label path="symbol">Symbol</form:label>
				<form:input path="symbol" class="form-control" />
 				<form:errors cssClass="error" path="symbol"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Show Chart</button>
				<button type="reset" class="btn btn-default">Clear</button>
			</div>
			
		</form:form>
		
	</div>
	
</div>

<%@include file="include/footer.jsp" %>
