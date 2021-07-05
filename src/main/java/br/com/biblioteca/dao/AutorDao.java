package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.modelo.Autor;

public class AutorDao extends AbstractDao {

	public Collection<Autor> findAll() {
		return getDataBase().getAllAutores();
	}

	public Autor find(Integer id) throws BibliotecaException {
		return findByPrimaryKey(Autor.class, id);
	}

	public void remover(Integer integer) {
		getDataBase().deletarAutor(integer);
	}

	public void salvar(Autor autor) {
		getDataBase().cadastrarAutor(autor);
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
