package br.com.biblioteca.arq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CurrentRequestTest {

	@Test
	public void contextoTemQueSerBibliotecaQuandoAcessandoRaizDaAplicacao() {
		String uri = "/biblioteca/";
		CurrentRequest cr = new CurrentRequest(uri, new String[] { "public", "index.jsp" });

		assertEquals("biblioteca", cr.getContextRoot());
	}

	@Test
	public void controllerTemQueSerSegundoElementoDaURI() {
		String segundoElemento = "livro";
		String uri = "/biblioteca/" + segundoElemento;
		CurrentRequest cr = new CurrentRequest(uri, new String[] { "public", "index.jsp" });

		assertEquals(segundoElemento, cr.getControlller());
	}

	@Test
	public void ActionTemQueSerTerceiroElementoDaURI() {
		String segundoElemento = "livro";
		String terceiroElemento = "cadastrar";
		String uri = "/biblioteca/" + segundoElemento + "/" + terceiroElemento;

		CurrentRequest cr = new CurrentRequest(uri, new String[] { "public", "index.jsp" });

		assertEquals(terceiroElemento, cr.getAction());
	}

	@Test
	public void EntityIdTemQueSerQuartElementoDaURI() {
		String segundoElemento = "livro";
		String terceiroElemento = "editar";
		String quartoElemento = "356";

		String uri = "/biblioteca/" + segundoElemento + "/" + terceiroElemento + "/" + quartoElemento;

		CurrentRequest cr = new CurrentRequest(uri, new String[] { "public", "index.jsp" });

		assertEquals(Integer.valueOf(quartoElemento), cr.getEntityId());
	}

	@Test
	public void todosOsOutrosMembrosDaURITemQueSeremNulosQuandoNaoInformados() {
		String uri = "/biblioteca/";
		CurrentRequest cr = new CurrentRequest(uri, new String[] { "public", "index.jsp" });

		assertEquals(null, cr.getControlller());
		assertEquals(null, cr.getAction());
		assertEquals(null, cr.getEntityId());
	}

	@Test
	public void todosOsOutrosMembrosDaURITemQueSeremNulosQuandoAcessandoURLPublica() {
		String uri = "/biblioteca/index.jsp";
		CurrentRequest cr = new CurrentRequest(uri, new String[] { "public", "index.jsp" });

		assertTrue(cr.isAllowedAccess());
		assertEquals(null, cr.getControlller());
		assertEquals(null, cr.getAction());
		assertEquals(null, cr.getEntityId());

		uri = "/biblioteca/public/assets/css/style.css";
		cr = new CurrentRequest(uri, new String[] { "public", "index.jsp" });

		assertTrue(cr.isAllowedAccess());
		assertEquals(null, cr.getControlller());
		assertEquals(null, cr.getAction());
		assertEquals(null, cr.getEntityId());

	}

	@Test
	public void parametrosNaURINaoDevemInterferirNoRoteamento() {
		String segundoElemento = "livro";
		String terceiroElemento = "editar";		

		String uri = "/biblioteca/" + segundoElemento + "/" + terceiroElemento + ";8O8488WlWWgNzAkGCFYAZyj3Bn91CR=05493D28DDD97308D66DAAC3DD66EAC2";

		CurrentRequest cr = new CurrentRequest(uri, new String[] { "public", "index.jsp" });

		assertEquals(terceiroElemento, cr.getAction());
		
		
		uri = "/biblioteca/" + segundoElemento + "/" + terceiroElemento + "?idLivro=458";
		cr = new CurrentRequest(uri, new String[] { "public", "index.jsp" });

		assertEquals(terceiroElemento, cr.getAction());
	}

}
