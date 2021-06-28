package br.com.biblioteca.controller;

import java.text.ParseException;
import java.util.Collection;

import br.com.biblioteca.dao.AutorDao;
import br.com.biblioteca.modelo.Autor;

public class AutorController extends AbstractController {

	private Autor obj;

	public AutorController() {
		obj = new Autor();
	}

	public void listar() {
		setRequestAttribute("autores", buscarTodosAutores());

		forward("autor/listar");
	}

	public void cadastrar() {

	}

	public void salvar() throws ParseException {

	}

	public Collection<Autor> buscarTodosAutores() {
		return getDao(AutorDao.class).findAll();
	}
}
