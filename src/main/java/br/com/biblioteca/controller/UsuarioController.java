package br.com.biblioteca.controller;

import br.com.biblioteca.dao.UsuarioDao;
import br.com.biblioteca.modelo.Usuario;

public class UsuarioController extends AbstractController{

	public Usuario validarLoginUsuario(String login, String senha) {
		return getDao(UsuarioDao.class).findByLoginAndPass(login, senha);
	}
}
