<%@ include file="../template/header.jsp"%>

<div class="container">

	<form method="POST" action="<c:url value="${nextAction}" />"  >		
		
		<br/>
		
		<div class="form-group">
			
			<label for="nome">Nome: </label> 
			<input type="text" class="form-control" name="nome" id="nome" placeholder="Nome da Editora" value="${editora.nome}" />
				
		</div>			
		
		<br/>
		<div class="form-group">
			<h4>Endereço</h4>
			<hr/>
		</div>
		
		<div class="form-group">
			
			<label for="rua">Logradouro: </label> 
			<input type="text" class="form-control" name="rua" id="rua" placeholder="Logradouro ex. Rua, Avenida, Alameda..." value="${editora.endereco.rua}" />
				
		</div>
		
		<div class="form-group">
			
			<label for="bairro">Bairro: </label> 
			<input type="text" class="form-control" name="bairro" id="bairro" placeholder="Bairro" value="${editora.endereco.bairro}" />
				
		</div>
		
		<div class="form-group">
			
			<label for="bairro">Cidade: </label> 
			<input type="text" class="form-control" name="cidade" id="cidade" placeholder="Cidade" value="${editora.endereco.cidade}" />
				
		</div>
		
		<div class="form-group">
			
			<label for="uf">Unidade Federativa: </label> 
			<select name="uf" class="form-control" id="uf">
				<option value="-1"> -- Selecione -- </option>			
				<c:forEach var="uf" items="${UFs}">
					<option value="${uf.sigla}" ${editora.endereco.uf == uf.sigla ? "selected" : "" } >
						${uf.nome}
					</option>
				</c:forEach>
			</select>
			
		</div>
		
		<div class="btn-group pull-right">			
			<button type="reset" class="btn btn-warning ">Limpar</button>
			<button type="submit" class="btn btn-primary ">Salvar</button>
		</div>
		
	</form>
</div>

<%@ include file="../template/footer.jsp"%>
