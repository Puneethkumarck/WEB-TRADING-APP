<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Member Enquiry:</h3>
	</div>
	
	<div class="panel-body">

		<table class="table table-striped" id="member-table">
			<thead>
				<tr>
					<th>Username</th>
					<th>Email</th>
					<th>Full Name</th>
					<th>Password</th>
					<th>Role</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty members}">
					<c:forEach var="member" items="${members}">
						<tr>
							<td>${member.username}</td>
							<td>${member.email}</td>
							<td>${member.fullname}</td>
							<td>${member.password}</td>
							<td>${member.role}</td>
						</tr>
					</c:forEach>
				</c:if>
		
			</tbody>
		</table>

	</div>
	
</div>

<%@include file="include/footer.jsp" %>
