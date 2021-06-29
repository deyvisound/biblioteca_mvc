<%@ include file="../template/header.jsp"%>

<div class="container">
	<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Endereço</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${autores}" var="autor">
				<tr>
					<td>${autor.id}</td>
					<td>${autor.nome}</td>
					<td>${autor.endereco}</td>
					<td>
						<a href="<c:url value="/autor/editar/${autor.id}" />">
							<img src="<c:url value="/public/assets/img/edit.png" />" alt="editar" width="15">
						</a>
						&nbsp;
						<a href="<c:url value="/autor/remover/${autor.id}" />">
							<img src="<c:url value="/public/assets/img/remove.png" />" alt="remover" width="15">
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
</div>

<%@ include file="../template/footer.jsp"%>