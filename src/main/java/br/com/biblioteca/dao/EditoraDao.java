package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.arq.BancoDeDados;
import br.com.biblioteca.modelo.Editora;

public class EditoraDao implements AbstractDao {

	@SuppressWarnings("unchecked")
	public Collection<Editora> findAll() {
		return BancoDeDados.getAllEditoras();
	}

	public Editora find(Integer id) {
		return BancoDeDados.findEditoraById(id);
	}

}
