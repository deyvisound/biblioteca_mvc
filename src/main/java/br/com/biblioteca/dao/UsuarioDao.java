package br.com.biblioteca.dao;

import br.com.biblioteca.modelo.Usuario;

public class UsuarioDao extends AbstractDao {

	public Usuario findByLoginAndPass(String login, String senha) {
		return getDataBase().findUserByLoginAndPass(login, senha);
	}

}
