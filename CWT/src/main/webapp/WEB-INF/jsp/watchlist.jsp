<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Watchlist:</h3>
	</div>
	
	<div class="panel-body">
	
		<h4 class="panel-title">New Record</h4>
		
		<br/>
		
		<form:form action="${pageContext.request.contextPath}/admin/watchlist/create" class="form" modelAttribute="watchlistForm" role="form">
			
			<div class="form-group">
				<form:label path="symbol">Symbol</form:label>
				<form:input path="symbol" class="form-control" />
 				<form:errors cssClass="error" path="symbol"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Add</button>
				<button type="reset" class="btn btn-default">Clear</button>
			</div>
			
		</form:form>
		
	</div>
	
	<div class="panel-body">

		<table class="table table-striped" id="watchlist-table">
			<thead>
				<tr>
					<th>Symbol</th>
					<th>Active</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty watchlists}">
					<c:forEach var="watchlist" items="${watchlists}">
						<tr>
							<td>${watchlist.symbol}</td>
							<td>${watchlist.active}</td>
							<td><a href="/admin/watchlist/edit?symbol=${watchlist.symbol}">Edit</a> | <a href="" onclick="deleteSymbol('/admin/watchlist/delete?symbol=${watchlist.symbol}')">Delete</a></td>
						</tr>
					</c:forEach>
				</c:if>
		
			</tbody>
		</table>

	</div>
	
</div>

<script type="text/javascript">
function go(url)
{
    window.location = url;
}

function deleteSymbol(url)
{
    var isOK = confirm("Are you sure to delete?");
    if(isOK)
    {
        go(url);
    }
}
</script>

<%@include file="include/footer.jsp" %>
