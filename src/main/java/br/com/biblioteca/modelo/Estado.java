package br.com.biblioteca.modelo;

import java.util.Arrays;
import java.util.List;

public enum Estado {
	
	RONDONIA 	        (11,"Rondônia" 	          ,"RO"),
	ACRE 	            (12,"Acre" 	              ,"AC"),
	AMAZONAS 	        (13,"Amazonas" 	          ,"AM"),
	RORAIMA 	        (14,"Roraima" 	          ,"RR"),
	PARA 	            (15,"Pará" 	              ,"PA"),
	AMAPA	            (16,"Amapá"	              ,"AP"),
	TOCANTINS 	        (17,"Tocantins" 	      ,"TO"),
	MARANHAO 	        (21,"Maranhão" 	          ,"MA"),
	PIAUI 	            (22,"Piauí" 	          ,"PI"),
	CEARA 	            (23,"Ceará" 	          ,"CE"),
	RIO_GRANDE_DO_NORTE (24,"Rio Grande do Norte" ,"RN"),
	PARAIBA 	        (25,"Paraíba" 	          ,"PB"),
	PERNAMBUCO 	        (26,"Pernambuco" 	      ,"PE"),
	ALAGOAS 	        (27,"Alagoas" 	          ,"AL"),
	SERGIPE 	        (28,"Sergipe" 	          ,"SE"),
	BAHIA 	            (29,"Bahia" 	          ,"BA"),
	MINAS_GERAIS 	    (31,"Minas Gerais" 	      ,"MG"),
	ESPIRITO_SANTO 	    (32,"Espírito Santo" 	  ,"ES"),
	RIO_DE_JANEIRO 	    (33,"Rio de Janeiro" 	  ,"RJ"),
	SAO_PAULO 	        (35,"São Paulo" 	      ,"SP"),
	PARANA 	            (41,"Paraná" 	          ,"PR"),
	SANTA_CATARINA 	    (42,"Santa Catarina" 	  ,"SC"),
	RIO_GRANDE_DO_SUL 	(43,"Rio Grande do Sul"   ,"RS"),
	MATO_GROSSO_DO_SUL 	(50,"Mato Grosso do Sul"  ,"MS"),
	MATO_GROSSO 	    (51,"Mato Grosso" 	      ,"MT"),
	GOIAS 	            (52,"Goiás" 	          ,"GO"),
	DISTRITO_FEDERAL 	(53,"Distrito Federal" 	  ,"DF");
	
	private int id;
	private String nome;
	private String sigla;

	private Estado(int id, String nome, String sigla) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Estado> getAll() {
		return Arrays.asList(Estado.values());
	}
	
}
