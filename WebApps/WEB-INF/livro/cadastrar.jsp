<%@ include file="../template/header.jsp"%>

<div class="container">

	<form method="POST" action="<c:url value="${nextAction}" />"  >		
		
		<div class="form-group">
			
			<label for="titulo">Título: </label> 
			<input type="text" class="form-control" name="titulo" id="titulo" placeholder="Título do livro" value="${livro.titulo}" />
				
		</div>
		
		<div class="form-group">
			
			<label for="assunto">Assunto: </label> 
			<input type="text" class="form-control" name="assunto" id="assunto" placeholder="Qual o assunto do livro?" value="${livro.assunto}" />
				
		</div>
		
		<div class="form-group">
			
			<label for="autor">Autor: </label> 
			<select name="id_autor" class="form-control">
				<option value="-1"> -- Selecione -- </option>			
				<c:forEach var="autor" items="${autores}">
					<option value="${autor.id}" ${livro.autor.id == autor.id ? "selected" : "" } >
						${autor}
					</option>
				</c:forEach>
			</select>
			
		</div>
		
		<div class="form-group">
			
			<label for="editora">Editora: </label> 
			<select name="id_editora" class="form-control">
				<option value="-1"> -- Selecione -- </option>			
				<c:forEach var="editora" items="${editoras}">
					<option value="${editora.id}" ${livro.editora.id == editora.id ? "selected" : "" } >
						${editora}
					</option>
				</c:forEach>
			</select>
			
		</div>
		
		
		<div class="form-group">
			
			<label for="dataPublicacao">Data Publicação: </label> 
			<input type="text" class="form-control datepicker" name="dataPublicacao" 
				id="dataPublicacao" placeholder="dd/mm/yyyy" value="<fmt:formatDate pattern = "dd/MM/yyyy" value = "${livro.dataPublicacao}" />" />
				
		</div>
		
		<div class="btn-group pull-right">			
			<button type="reset" class="btn btn-warning ">Limpar</button>
			<button type="submit" class="btn btn-primary ">Salvar</button>
		</div>
		
	</form>
</div>

<%@ include file="../template/footer.jsp"%>
