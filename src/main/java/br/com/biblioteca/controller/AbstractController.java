package br.com.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.biblioteca.arq.CurrentRequest;
import br.com.biblioteca.arq.ListaMensagens;

public abstract class AbstractController {

	private HttpServletRequest request;

	private boolean isForward;
	private boolean isRedirect;

	private String forwardFile;
	private String redirectURI;

	public AbstractController() {
		this.isForward = false;
		this.isRedirect = false;
	}

	protected void forward(String destino) {
		this.isForward = true;
		this.forwardFile = destino + ".jsp";
	}

	protected void redirect(String destino) {
		this.isRedirect = true;
		this.redirectURI = destino;
	}

	public boolean isForward() {
		return isForward;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public String getForwardFile() {
		return "/WEB-INF/" + forwardFile;
	}

	public String getRedirectURI() {
		return redirectURI;
	}

	/**
	 * 
	 * @param classDao
	 * @return
	 */
	protected <T> T getDao(Class<T> classDao) {
		try {
			return classDao.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Set a ação que o formulário renderizado irá utilizar
	 * 
	 * @param action
	 */
	protected void setActionForm(String action) {
		this.setRequestAttribute("nextAction", "/" + action);
	}

	/**
	 * Retorna um parâmetro dado seu nome
	 * 
	 * @param param
	 * @return
	 */
	protected String getParam(String param) {
		return this.request.getParameter(param);
	}

	protected Object getSessionAtribute(String attrName) {
		return this.request.getSession().getAttribute(attrName);
	}

	public void setRequestAndResponse(HttpServletRequest req, HttpServletResponse resp) {
		this.request = req;
	}

	public void setRequestAttribute(String key, Object value) {
		request.setAttribute(key, value);
	}

	protected void setSessionAttribute(String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	protected void destroySessionAttribute(String key) {
		request.getSession().removeAttribute(key);
	}

	protected CurrentRequest getCurrentRequestObj() {
		return (CurrentRequest) this.request.getAttribute("currentRequestObj");
	}
	
	/**
	 * @param message
	 */
	@SuppressWarnings("unchecked")
	protected void addErrorMenssage(String message) {
		
		Object listaMensagensObject = getSessionAtribute("lista_mensagens");
		List<ListaMensagens> listaMensagens;
		
		if(listaMensagensObject != null)
			listaMensagens = (List<ListaMensagens>) listaMensagensObject;
		else
			listaMensagens = new ArrayList<ListaMensagens>();
		
		listaMensagens.add( new ListaMensagens(ListaMensagens.ERROR, message) );
		setSessionAttribute("lista_mensagens", listaMensagens);
	}
	
	@SuppressWarnings("unchecked")
	protected boolean hasErrors() {
		Object listaMensagensObject = getSessionAtribute("listaMensagens");
		List<ListaMensagens> listaMensagens;
		
		if(listaMensagensObject == null)
			return false;
		else
			listaMensagens = (List<ListaMensagens>) listaMensagensObject;
		
		for(ListaMensagens lm : listaMensagens)
			if(lm.isError())
				return true;
		
		return false;				
	}

}
