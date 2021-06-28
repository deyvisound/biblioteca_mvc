<%@ include file="../template/header.jsp"%>

<div class="container">
	<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Endereço</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${autores}" var="autor">
				<tr>
					<td>${autor.id}</td>
					<td>${autor.nome}</td>
					<td>${autor.endereco}</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
</div>

<%@ include file="../template/footer.jsp"%>