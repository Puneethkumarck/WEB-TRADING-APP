<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    
    <link href="/css/ichimoku.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cassandra Web Trader</title>
</head>
<body>

	<div class="container">
      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Cassandra Web Trader</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="active"><a href="${pageContext.request.contextPath}/home">Home</a></li>
              <security:authorize access="isAnonymous()">
	              <li><a href="${pageContext.request.contextPath}/member/register">Register</a></li>
	              <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
	              <li><a href="${pageContext.request.contextPath}/forgotpassword">Forgot Password</a></li>
              </security:authorize>
              <security:authorize access="isAuthenticated()">
	              <li>
	              	<c:url var="logoutUrl" value="/logout" />
	              	<form:form id="logoutForm" action="${logoutUrl}" method="post"></form:form>
	              	<a href="#" onclick="document.getElementById('logoutForm').submit()">Logout</a>
	              </li>
	              <li><a href="${pageContext.request.contextPath}/member/chart">Chart</a></li>
	              <li><a href="${pageContext.request.contextPath}/member/signalhistory">Signal History</a></li>
	          </security:authorize>
              <security:authorize access="hasRole('ROLE_ADMIN')">
	              <li><a href="${pageContext.request.contextPath}/admin/watchlist">Watchlist</a></li>
	              <li><a href="${pageContext.request.contextPath}/admin/memberenquiry">Member Enquiry</a></li>
              </security:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li class="active"><a href="./">Default <span class="sr-only">(current)</span></a></li>
               <li><a href="?language=en">English</a></li>
              <li><a href="?language=es">Spanish</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
      
      <c:if test="${not empty flashMessage}">
      	<div class="alert alert-${flashType} alert-dismissable">
      		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
      		${flashMessage}
      	</div>
      </c:if>