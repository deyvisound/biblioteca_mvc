<%@ include file="../template/header.jsp"%>

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
				<td>${livro.editora}</td>
				<td>${livro.autor}</td>
				<td>
					<fmt:formatDate pattern="dd/MM/yyyy"
						value="${livro.dataPublicacao}" />
				</td>
				<td>
					<img src="/public/assets/img/edit.png" alt="editar" width="30">
				</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<%@ include file="../template/footer.jsp"%>