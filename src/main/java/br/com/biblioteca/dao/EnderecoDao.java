package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.arq.BancoDeDados;
import br.com.biblioteca.modelo.Autor;
import br.com.biblioteca.modelo.Endereco;

public class EnderecoDao implements AbstractDao {
	
	@SuppressWarnings("unchecked")
	public Collection<Autor> findAll() {
		return BancoDeDados.getAllAutores();
	}

	public Endereco find(Integer id) {
		return BancoDeDados.findEnderecoById(id);
	}

	public void remover(Integer idEndereco) {
		BancoDeDados.deletarEndereco(idEndereco);
	}

	public void salvar(Autor obj) {
		BancoDeDados.cadastrarAutor(obj);
	}

}
