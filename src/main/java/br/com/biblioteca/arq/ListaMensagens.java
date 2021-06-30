package br.com.biblioteca.arq;

/**
 * Mensagem de erro do sistema
 * @author deyvison
 */
public class ListaMensagens {

	public static Integer ERROR = 400;
	public static Integer WARNING = 500;
	public static Integer SUCCESS = 200;

	private Integer codigo;
	private String mensagem;
	
	public ListaMensagens(Integer cod, String msg) {
		this.codigo = cod;
		this.mensagem = msg;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isError() {
		return ERROR.equals(getCodigo());
	}

	public boolean isWarnig() {
		return WARNING.equals(getCodigo());
	}

	public boolean isSuccess() {
		return SUCCESS.equals(getCodigo());
	}
}
