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

		forward("livro/listar");

	}

	/**
	 * Direciona o usuário ao formulário de cadastro de livro
	 */
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
		forward("livro/cadastrar");
	}
	
	/**
	 * Direciona o usuário ao formulário de cadastro de livro
	 */
	public void editar() {

		Livro livro = getDao(LivroDao.class).find( getCurrentRequestObj().getEntityId());

		Collection<Autor> autores = getDao(AutorDao.class).findAll();
		Collection<Editora> editoras = getDao(EditoraDao.class).findAll();

		setRequestAttribute("livro", livro);
		setRequestAttribute("autores", autores);
		setRequestAttribute("editoras", editoras);

		setActionForm("livro/salvar/" + livro.getId());
		forward("livro/cadastrar");
	}

	/**
	 * Salva novo livro no banco de dados
	 * @throws ParseException
	 */
	public void salvar() throws ParseException {

		Autor autor = getDao(AutorDao.class).find(Integer.valueOf(getParam("id_autor")));
		Editora editora = getDao(EditoraDao.class).find(Integer.valueOf(getParam("id_editora")));

		obj = new Livro(getCurrentRequestObj().getEntityId());
		obj.setTitulo(getParam("titulo"));
		obj.setAssunto(getParam("assunto"));
		obj.setDataPublicacao(BibliotecaHelper.getDateFromString(getParam("dataPublicacao")));
		obj.setAutor(autor);
		obj.setEditora(editora);

		getDao(LivroDao.class).merge(obj);

		redirect("livro/listar");
	}

	public void remover() {
		getDao(LivroDao.class).remover( getCurrentRequestObj().getEntityId() );
		redirect("livro/listar");
	}

}
