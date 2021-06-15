<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
	<title>Biblioteca MVC</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<style>
		.active{background-color: #ccd9f0;}
	</style>
	
</head>

<body>

	<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/" />">${fn:toUpperCase(context_root)}</a>
			</div>
			
			<ul class="nav navbar-nav">
				<li class="${empty current_controller ? 'active' : '' }">
					<a href="<c:url value="/" />">Home</a>
				</li>
				<li class="${current_controller == 'livro' ? 'active' : '' }">
					<a href="<c:url value="/livro/listar" />">Livros</a>
				</li>
				<li class="${current_controller == 'autor' ? 'active' : '' }">
					<a href="<c:url value="/autor/listar" />">Autores</a>
				</li>
				<li class="${current_controller == 'editora' ? 'active' : '' }">
					<a href="<c:url value="/editora/listar" />">Editoras</a>
				</li>
			</ul>

		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class=" col-md-12 ">
				<h4>
					${context_root}
					<c:if test="${not empty current_controller}"> 
							-> ${current_controller} 
						</c:if>
					<c:if test="${not empty current_action}">
							-> ${current_action}
						</c:if>
					:
				</h4>
				<hr />
				<br />