<%@ include file="../template/header.jsp"%>

<table class="table">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Endereço</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${autores}" var="autor">
			<tr>
				<td>${autor.nome}</td>
				<td>${autor.endereco}</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<%@ include file="../template/footer.jsp"%>