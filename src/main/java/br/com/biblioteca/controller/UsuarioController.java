package br.com.biblioteca.controller;

import br.com.biblioteca.dao.UsuarioDao;
import br.com.biblioteca.modelo.Usuario;
import br.com.biblioteca.utils.BibliotecaHelper;

public class UsuarioController extends AbstractController{

	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 */
	public boolean isUsuarioValido(String login, String senha) {
		Usuario user = buscarPorLoginSenha(login, senha);
		
		if(BibliotecaHelper.isEmpty(user))
				return false;
		
		return true;
	}

	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 */
	public Usuario buscarPorLoginSenha(String login, String senha) {
		return getDao(UsuarioDao.class).findByLoginAndPass(login, senha);
	}
}
