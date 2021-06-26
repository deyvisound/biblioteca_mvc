package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.arq.BancoDeDados;
import br.com.biblioteca.modelo.Usuario;

public class UsuarioDao implements AbstractDao {

	public Usuario findByLoginAndPass(String login, String senha) {
		return BancoDeDados.findUserByLoginAndPass(login, senha);
	}

	@Override
	public <T> Collection<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
