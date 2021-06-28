package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.arq.BancoDeDados;
import br.com.biblioteca.modelo.Livro;

public class LivroDao implements AbstractDao {

	@SuppressWarnings("unchecked")
	public Collection<Livro> findAll() {
		return BancoDeDados.getAllLivros();
	}

	public Livro find(Integer id) {
		return BancoDeDados.findLivroById(id);
	}

	public void merge(Livro livro) {
		if (livro.getId() <= 0)
			this.salvar(livro);
		else
			this.atualizar(livro);
	}

	public void salvar(Livro livro) {
		BancoDeDados.cadastrarLivro(livro);
	}

	public void atualizar(Livro livro) {
		BancoDeDados.deletarLivro(livro.getId());
		BancoDeDados.cadastrarLivro(livro);
	}

	public void remover(Integer integer) {
		BancoDeDados.deletarLivro(integer);
	}

}
