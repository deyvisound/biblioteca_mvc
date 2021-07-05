package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.modelo.Autor;
import br.com.biblioteca.modelo.Endereco;

public class EnderecoDao extends AbstractDao {

	public Collection<Autor> findAll() {
		return getDataBase().getAllAutores();
	}

	public Endereco find(Integer id) throws BibliotecaException {
		return findByPrimaryKey(Endereco.class, id);
	}

	public void remover(Integer idEndereco) {
		getDataBase().deletarEndereco(idEndereco);
	}

	public void salvar(Autor obj) {
		getDataBase().cadastrarAutor(obj);
	}

}
