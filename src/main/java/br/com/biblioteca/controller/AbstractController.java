package br.com.biblioteca.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.biblioteca.arq.SessionParams;

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

	protected void setForward(String destino) {
		this.isForward = true;
		this.forwardFile = destino + ".jsp";
	}

	protected void setRedirect(String destino) {
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
		String context = (String) this.request.getSession().getAttribute(SessionParams.CONTEXT_ROOT);
		return "/" + context + redirectURI;
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

	public void setRequestAndResponse(HttpServletRequest req, HttpServletResponse resp) {
		this.request = req;
	}

	public void setRequestAttribute(String key, Object value) {
		request.setAttribute(key, value);
	}

}
