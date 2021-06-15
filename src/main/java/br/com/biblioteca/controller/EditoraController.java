package br.com.biblioteca.controller;

import java.text.ParseException;
import java.util.Collection;

import br.com.biblioteca.dao.EditoraDao;
import br.com.biblioteca.modelo.Editora;

public class EditoraController extends AbstractController {

	private Editora obj;

	public EditoraController() {
		obj = new Editora();
	}

	public void listar() {

		Collection<Editora> editoras = getDao(EditoraDao.class).findAll();
		setRequestAttribute("editoras", editoras);

		setForward("editora/listar");

	}

	public void cadastrar() {

	}

	public void salvar() throws ParseException {

	}

}
