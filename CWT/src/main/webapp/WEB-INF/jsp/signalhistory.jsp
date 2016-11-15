<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Trading Signal History:</h3>
	</div>
	
	<div class="panel-body">
	
		<h4 class="panel-title">Please select:</h4>
		
		<br/>
		
		<form:form action="${pageContext.request.contextPath}/member/signalhistory" class="form" modelAttribute="chart" role="form">
			
			<div class="form-group">
				<form:label path="symbol">Symbol</form:label>
				<form:input path="symbol" class="form-control" />
 				<form:errors cssClass="error" path="symbol"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Get</button>
				<button type="reset" class="btn btn-default">Clear</button>
			</div>
			
		</form:form>
		
	</div>
	
	<div class="panel-body">

		<table class="table table-striped" id="signalhistory-table">
			<thead>
				<tr>
					<th>Signal Date</th>
					<th>Signal Price</th>
					<th>Signal Name</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty signalhistory}">
					<c:forEach var="signal" items="${signalhistory}">
						<tr>
							<td>${signal.signalTime}</td>
							<td>${signal.price}</td>
							<td>${signal.signalName}</td>
						</tr>
					</c:forEach>
				</c:if>
		
			</tbody>
		</table>

	</div>
	
</div>

<%@include file="include/footer.jsp" %>
