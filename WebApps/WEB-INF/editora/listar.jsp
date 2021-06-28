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
			<c:forEach items="${editoras}" var="editora">
				<tr>
					<td>${editora.id}</td>
					<td>${editora.nome}</td>
					<td>${editora.endereco}</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
</div>

<%@ include file="../template/footer.jsp"%>