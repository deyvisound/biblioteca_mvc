package br.com.biblioteca.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.biblioteca.servlets.AppEnum;

public abstract class AbstractController {

	private HttpServletRequest request;

	private boolean isForward;
	private boolean isRedirect;

	private String jspDestino;

	public AbstractController() {
		this.isForward = false;
		this.isRedirect = false;
	}

	protected void setForward(String jspDestino) {
		this.isForward = true;
		this.jspDestino = jspDestino + ".jsp";
	}

	protected void setRedirect(String jspDestino) {
		this.isRedirect = true;
		this.jspDestino = jspDestino;
	}

	protected <T> T getDao(Class<T> classDao) {
		try {
			return classDao.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void setProximaAcao(String action) {
		String controller = (String) this.request.getAttribute(AppEnum.CONTROLLER.toString());

		this.setRequestAttribute("nextAction", "/" + controller + "/" + action);
	}
	
	/**
	 * Retorna um parâmetro dado seu nome
	 * @param param
	 * @return
	 */
	protected String getParam(String param) {
		return this.request.getParameter(param);
	}

	public void setRequestAndResponse(HttpServletRequest req, HttpServletResponse resp) {
		this.request = req;
	}

	public String getJspDestino() {
		return jspDestino;
	}

	public boolean isForward() {
		return isForward;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRequestAttribute(String key, Object value) {
		request.setAttribute(key, value);
	}

}
