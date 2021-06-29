<%@ include file="../template/header.jsp"%>

<div class="container">

	<table class="table">
		<thead>
			<tr>
				<th>Titulo</th>
				<th>Assunto</th>
				<th>Editora</th>
				<th>Autor</th>
				<th>Data Publicação</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${livros}" var="livro">
				<tr>
					<td>${livro.titulo}</td>
					<td>${livro.assunto}</td>
					<td>${livro.editora.nome}</td>
					<td>${livro.autor.nome}</td>
					<td>
						<fmt:formatDate pattern="dd/MM/yyyy"
							value="${livro.dataPublicacao}" />
					</td>
					<td>
						<a href="<c:url value="/livro/editar/${livro.id}" />">
							<img src="<c:url value="/public/assets/img/edit.png" />" alt="editar" width="15">
						</a>
						&nbsp;
						<a href="<c:url value="/livro/remover/${livro.id}" />">
							<img src="<c:url value="/public/assets/img/remove.png" />" alt="remover" width="15">
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
</div>
<%@ include file="../template/footer.jsp"%>