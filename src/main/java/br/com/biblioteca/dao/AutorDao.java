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

}
