package br.com.biblioteca.controller;

import java.text.ParseException;
import java.util.Collection;

import br.com.biblioteca.dao.AutorDao;
import br.com.biblioteca.dao.EditoraDao;
import br.com.biblioteca.dao.LivroDao;
import br.com.biblioteca.modelo.Autor;
import br.com.biblioteca.modelo.Editora;
import br.com.biblioteca.modelo.Livro;
import br.com.biblioteca.utils.BibliotecaHelper;

public class LivroController extends AbstractController {

	private Livro obj;

	public LivroController() {
		obj = new Livro();
	}

	public void listar() {

		Collection<Livro> livros = getDao(LivroDao.class).findAll();
		setRequestAttribute("livros", livros);

		setForward("livro/listar");

	}

	public void cadastrar() {

		Livro livro = new Livro();
		livro.setAutor(new Autor());
		livro.setEditora(new Editora());

		Collection<Autor> autores = getDao(AutorDao.class).findAll();
		Collection<Editora> editoras = getDao(EditoraDao.class).findAll();

		setRequestAttribute("livro", livro);
		setRequestAttribute("autores", autores);
		setRequestAttribute("editoras", editoras);

		setActionForm("livro/salvar");
		setForward("livro/cadastrar");
	}

	public void salvar() throws ParseException {

		Autor autor = getDao(AutorDao.class).find(Integer.valueOf(getParam("id_autor")));
		Editora editora = getDao(EditoraDao.class).find(Integer.valueOf(getParam("id_editora")));

		obj = new Livro();
		obj.setTitulo(getParam("titulo"));
		obj.setAssunto(getParam("assunto"));
		obj.setDataPublicacao(BibliotecaHelper.getDateFromString(getParam("dataPublicacao")));
		obj.setAutor(autor);
		obj.setEditora(editora);

		getDao(LivroDao.class).salvar(obj);
		
		setRedirect("livro/listar");
	}

}
