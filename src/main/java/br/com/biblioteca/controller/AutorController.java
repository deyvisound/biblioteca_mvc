package br.com.biblioteca.controller;

import java.text.ParseException;
import java.util.Collection;

import br.com.biblioteca.dao.AutorDao;
import br.com.biblioteca.modelo.Autor;
import br.com.biblioteca.modelo.Endereco;
import br.com.biblioteca.modelo.Estado;

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

		setRequestAttribute("autor", new Autor());
		setRequestAttribute("endereco", new Endereco());
		setRequestAttribute("UFs", Estado.getAll());

		setActionForm("autor/salvar");
		forward("autor/cadastrar");

	}

	public void editar() {

		Autor autor = getDao(AutorDao.class).find(getCurrentRequestObj().getEntityId());

		setRequestAttribute("autor", autor);
		setRequestAttribute("UFs", Estado.getAll());

		setActionForm("autor/salvar/" + autor.getId());
		forward("autor/cadastrar");
	}

	public void salvar() throws ParseException {

		Endereco endereco = new Endereco();

		obj = new Autor(getCurrentRequestObj().getEntityId());
		obj.setNome(getParam("nome"));

		endereco.setRua(getParam("rua"));
		endereco.setBairro(getParam("bairro"));
		endereco.setCidade(getParam("cidade"));
		endereco.setUF(getParam("uf"));

		obj.setEndereco(endereco);

		getDao(AutorDao.class).merge(obj);

		redirect("autor/listar");
	}

	public void remover() {
		getDao(AutorDao.class).remover(getCurrentRequestObj().getEntityId());
		redirect("autor/listar");
	}

	public Collection<Autor> buscarTodosAutores() {
		return getDao(AutorDao.class).findAll();
	}
}
