package br.com.biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.biblioteca.modelo.Autor;
import br.com.biblioteca.modelo.Editora;
import br.com.biblioteca.modelo.Endereco;
import br.com.biblioteca.modelo.Livro;

/**
 * Classe que simula o banco de dados
 * 
 * @author deyvison
 *
 */
public class BancoDeDados {

	private static int chavePrimaria = 1;

	public static Map<Integer, Editora> editoras;
	public static Map<Integer, Livro> livros;
	public static Map<Integer, Autor> autores;
	public static Map<Integer, Endereco> enderecos;

	
	
	/**
	 * ################################## AUTORES
	 * 
	 * @param autor
	 */
	public static void cadastrarAutor(Autor autor) {
		if (autores == null)
			autores = new HashMap<>();

		autor.setId(chavePrimaria++);
		autores.put(autor.getId(), autor);
	}

	public static Collection<Autor> getAllAutores() {
		return autores.values();
	}

	public static Autor findAutorById(Integer id) {
		return autores.get(id);
	}

	
	
	/**
	 * ###################################### LIVROS
	 * 
	 * @param livro
	 */
	public static void cadastrarLivro(Livro livro) {
		if (livros == null)
			livros = new HashMap<>();

		livro.setId(chavePrimaria++);
		livros.put(livro.getId(), livro);
	}

	public static Collection<Livro> getAllLivros() {
		return livros.values();
	}

	
	
	/**
	 * ######################################## Endereço
	 * 
	 * @param endereco
	 */
	public static void cadastrarEndereco(Endereco endereco) {
		if (enderecos == null)
			enderecos = new HashMap<>();

		endereco.setId(chavePrimaria++);
		enderecos.put(endereco.getId(), endereco);
	}
	
	public static Collection<Endereco> getAllEnderecos() {
		return enderecos.values();
	}

	
	/**
	 * ####################################### Editora
	 * 
	 * @param editora
	 */
	public static void cadastrarEditora(Editora editora) {
		if (editoras == null)
			editoras = new HashMap<>();

		editora.setId(chavePrimaria++);
		editoras.put(editora.getId(), editora);
	}
	
	public static Collection<Editora> getAllEditoras() {
		return editoras.values();
	}
	
	public static Editora findEditoraById(Integer id) {
		return editoras.get(id);
	}
	

	/**
	 * 
	 * @throws ParseException
	 */
	public void popularBanco() throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Endereco enderecoEditora = new Endereco();
		enderecoEditora.setRua("Rua Alexandrino de Alencar");
		enderecoEditora.setBairro("Bairro das Goiabeiras");
		enderecoEditora.setCidade("Natal");
		enderecoEditora.setUF("RN");

		Endereco endereco = new Endereco();
		endereco.setRua("Rua Alexandrino de Alencar");
		endereco.setBairro("Bairro das Goiabeiras");
		endereco.setCidade("Natal");
		endereco.setUF("RN");

		Autor autor = new Autor();
		autor.setNome("Marco Antonio");
		autor.setEndereco(endereco);

		Editora editora = new Editora();
		editora.setNome("Alcântara");
		editora.setEndereco(enderecoEditora);

		Livro livro = new Livro();
		livro.setTitulo("Trigonometria");
		livro.setAssunto("Matemática - Formas geométricas");
		livro.setDataPublicacao(format.parse("23/11/2005"));
		livro.setAutor(autor);
		livro.setEditora(editora);

		BancoDeDados.cadastrarLivro(livro);
		BancoDeDados.cadastrarAutor(autor);
		BancoDeDados.cadastrarEditora(editora);
		BancoDeDados.cadastrarEndereco(endereco);
		BancoDeDados.cadastrarEndereco(enderecoEditora);

	}
}
