package br.com.biblioteca.dao;

import java.util.Collection;

import br.com.biblioteca.arq.BancoDeDados;
import br.com.biblioteca.modelo.Editora;

public class EditoraDao implements AbstractDao {

	@SuppressWarnings("unchecked")
	public Collection<Editora> findAll() {
		return BancoDeDados.getAllEditoras();
	}

	public Editora find(Integer id) {
		return BancoDeDados.findEditoraById(id);
	}

	public void salvar(Editora editora) {
		BancoDeDados.cadastrarEditora(editora);
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
		BancoDeDados.deletarEditora(idEditora);
	}

}
