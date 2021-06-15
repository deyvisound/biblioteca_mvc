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

		Collection<Autor> autores = getDao(AutorDao.class).findAll();
		setRequestAttribute("autores", autores);

		setForward("autor/listar");
	}

	public void cadastrar() {

	}

	public void salvar() throws ParseException {

	}

}
