package br.com.biblioteca.dao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.biblioteca.arq.BancoDeDados;
import br.com.biblioteca.modelo.Livro;

public class LivroDao implements AbstractDao {

	@SuppressWarnings("unchecked")
	public Collection<Livro> findAll() {
		return BancoDeDados.getAllLivros();
	}

	public Collection<Integer> findteste() {
		return new ArrayList<Integer>();
	}

	public void salvar(Livro livro) {
		BancoDeDados.cadastrarLivro(livro);
	}

}
