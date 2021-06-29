package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.arq.BancoDeDados;
import br.com.biblioteca.modelo.Autor;

public class AutorDao implements AbstractDao {
	
	@SuppressWarnings("unchecked")
	public Collection<Autor> findAll() {
		return BancoDeDados.getAllAutores();
	}

	public Autor find(Integer id) {
		return BancoDeDados.findAutorById(id);
	}

	public void remover(Integer integer) {
		BancoDeDados.deletarAutor(integer);
	}

	public void salvar(Autor autor) {
		BancoDeDados.cadastrarAutor(autor);
	}
	
	private void atualizar(Autor autor) {
		remover(autor.getId());
		salvar(autor);
	}

	public void merge(Autor autor) {
		if (autor.getId() <= 0)
			this.salvar(autor);
		else
			this.atualizar(autor);
	}

	

}
