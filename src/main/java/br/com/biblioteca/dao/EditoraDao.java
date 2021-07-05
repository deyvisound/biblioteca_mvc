package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.modelo.Editora;

public class EditoraDao extends AbstractDao {

	public Collection<Editora> findAll() {
		return getDataBase().getAllEditoras();
	}

	public Editora find(Integer id) throws BibliotecaException {
		return findByPrimaryKey(Editora.class, id);
	}

	public void salvar(Editora editora) {
		getDataBase().cadastrarEditora(editora);
	}

	private void atualizar(Editora editora) {
		remover(editora.getId());
		salvar(editora);
	}

	public void merge(Editora editora) {
		if (editora.getId() <= 0)
			this.salvar(editora);
		else
			this.atualizar(editora);
	}

	public void remover(int idEditora) {
		getDataBase().deletarEditora(idEditora);
	}

}
