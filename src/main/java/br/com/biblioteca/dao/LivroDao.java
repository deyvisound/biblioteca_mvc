package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.modelo.Livro;

public class LivroDao extends AbstractDao {

	public Collection<Livro> findAll() {
		return getDataBase().getAllLivros();
	}

	public Livro find(Integer id) throws BibliotecaException {
		return findByPrimaryKey(Livro.class, id);
	}

	public void merge(Livro livro) {
		if (livro.getId() <= 0)
			this.salvar(livro);
		else
			this.atualizar(livro);
	}

	public void salvar(Livro livro) {
		getDataBase().cadastrarLivro(livro);
	}

	public void atualizar(Livro livro) {
		getDataBase().deletarLivro(livro.getId());
		getDataBase().cadastrarLivro(livro);
	}

	public void remover(Integer integer) {
		getDataBase().deletarLivro(integer);
	}

}
