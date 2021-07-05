package br.com.biblioteca.arq;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.biblioteca.modelo.Autor;
import br.com.biblioteca.modelo.Editora;
import br.com.biblioteca.modelo.Endereco;
import br.com.biblioteca.modelo.Livro;
import br.com.biblioteca.modelo.Usuario;

/**
 * Classe que simula o banco de dados (Singleton)
 * @author deyvison
 */
public class DateBaseDriver {

	private int pKey = 1;
	private Map<Integer, Editora> editoras;
	private Map<Integer, Livro> livros;
	private Map<Integer, Autor> autores;
	private Map<Integer, Endereco> enderecos;
	private Map<Integer, Usuario> usuarios;

	private static DateBaseDriver instancia;
	
	private DateBaseDriver() {
		
	}
	
	public static synchronized DateBaseDriver getInstance() {
		if(instancia == null)
			instancia = new DateBaseDriver();
		
		return instancia;
	}
	
	
	/**
	 * ################################## AUTORES
	 * 
	 * @param autor
	 */
	public void cadastrarAutor(Autor autor) {
		if (autores == null)
			autores = new HashMap<>();

		if(autor.getId() <= 0)
			autor.setId(pKey++);
		
		autores.put(autor.getId(), autor);
	}

	public Collection<Autor> getAllAutores() {
		return autores.values();
	}

	public Autor findAutorById(Integer id) {
		return autores.get(id);
	}

	public void deletarAutor(Integer idAutor) {
		autores.remove(idAutor);
	}
	
	
	
	/**
	 * ###################################### LIVROS
	 * 
	 * @param livro
	 */
	public void cadastrarLivro(Livro livro) {
		if (livros == null)
			livros = new HashMap<>();

		if(livro.getId() <= 0)
			livro.setId(pKey++);
		
		livros.put(livro.getId(), livro);
	}

	public Collection<Livro> getAllLivros() {
		return livros.values();
	}
	
	public void deletarLivro(Integer idLivro) {
		livros.remove(idLivro);
	}
	
	public Livro findLivroById(Integer id) {
		return livros.get(id);
	}
	
	
	/**
	 * ######################################## Endereço
	 * 
	 * @param endereco
	 */
	public void cadastrarEndereco(Endereco endereco) {
		if (enderecos == null)
			enderecos = new HashMap<>();

		if(endereco.getId() <= 0)
			endereco.setId(pKey++);
		
		enderecos.put(endereco.getId(), endereco);
	}
	
	public Collection<Endereco> getAllEnderecos() {
		return enderecos.values();
	}

	public void deletarEndereco(Integer idEndereco) {
		enderecos.remove(idEndereco);
	}
	
	public Endereco findEnderecoById(Integer id) {
		return enderecos.get(id);
	}
	
	
	/**
	 * ####################################### Editora
	 * 
	 * @param editora
	 */
	public void cadastrarEditora(Editora editora) {
		if (editoras == null)
			editoras = new HashMap<>();

		if(editora.getId() <= 0)
			editora.setId(pKey++);
		
		editoras.put(editora.getId(), editora);
	}
	
	public Collection<Editora> getAllEditoras() {
		return editoras.values();
	}
	
	public Editora findEditoraById(Integer id) {
		return editoras.get(id);
	}
	
	public void deletarEditora(Integer idEditora) {
		editoras.remove(idEditora);
	}
	
	
	/**
	 * #########################################   Usuários
	 * 
	 */
	public void cadastrarUsuario(Usuario usuario) {
		if (usuarios == null)
			usuarios = new HashMap<>();

		if(usuario.getId() <= 0)
			usuario.setId(pKey++);
		
		usuarios.put(usuario.getId(), usuario);
	}

	public Usuario findUserByLoginAndPass(String login, String senha) {
		for(Integer idUser : usuarios.keySet()) {
			Usuario usuario = usuarios.get(idUser);
			
			if(usuario.getLogin().equals(login) && usuario.getSenha().equals(senha))
				return usuario;
		}
		
		return null;
	}		
	
	
	/**
	 * Iniciando banco de dados 
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
		
		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setSenha("admin");
		usuario.setNome("Administrador do Sistema"); 		
		
		DateBaseDriver bancoDeDados = DateBaseDriver.getInstance();
		bancoDeDados.cadastrarUsuario(usuario);
		bancoDeDados.cadastrarLivro(livro);
		bancoDeDados.cadastrarAutor(autor);
		bancoDeDados.cadastrarEditora(editora);
		bancoDeDados.cadastrarEndereco(endereco);
		bancoDeDados.cadastrarEndereco(enderecoEditora);
	}
}
