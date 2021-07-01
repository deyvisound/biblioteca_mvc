package br.com.biblioteca.controller;

import java.text.ParseException;
import java.util.Collection;

import br.com.biblioteca.dao.EditoraDao;
import br.com.biblioteca.modelo.Editora;
import br.com.biblioteca.modelo.Endereco;
import br.com.biblioteca.modelo.Estado;

public class EditoraController extends AbstractController {

	private Editora obj;

	public EditoraController() {
		obj = new Editora();
	}

	public void listar() {

		Collection<Editora> editoras = getDao(EditoraDao.class).findAll();
		setRequestAttribute("editoras", editoras);

		forward("editora/listar");

	}

	public void cadastrar() {

		setRequestAttribute("editora", new Editora());
		setRequestAttribute("endereco", new Endereco());
		setRequestAttribute("UFs", Estado.getAll());

		setActionForm("editora/salvar");
		forward("editora/cadastrar");

	}

	public void editar() {

		Editora editora = getDao(EditoraDao.class).find(getCurrentRequestObj().getEntityId());

		setRequestAttribute("editora", editora);
		setRequestAttribute("UFs", Estado.getAll());

		setActionForm("editora/salvar/" + editora.getId());
		forward("editora/cadastrar");
	}

	public void salvar() throws ParseException {

		Endereco endereco = new Endereco();

		obj = new Editora(getCurrentRequestObj().getEntityId());
		obj.setNome(getParam("nome"));

		endereco.setRua(getParam("rua"));
		endereco.setBairro(getParam("bairro"));
		endereco.setCidade(getParam("cidade"));
		endereco.setUF(getParam("uf"));

		obj.setEndereco(endereco);

		getDao(EditoraDao.class).merge(obj);

		redirect("editora/listar");
	}

	public void remover() {
		getDao(EditoraDao.class).remover(getCurrentRequestObj().getEntityId());
		redirect("editora/listar");
	}

	public Collection<Editora> buscarTodoseditoraes() {
		return getDao(EditoraDao.class).findAll();
	}

}
