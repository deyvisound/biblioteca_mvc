package br.com.biblioteca.arq;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.biblioteca.controller.AbstractController;
import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.utils.BibliotecaHelper;

public class Reflection {

	private HttpServletRequest request;
	private HttpServletResponse response;

	private AbstractController controller;
	private Method method;
	private CurrentRequest currentRequestObj;

	private static final String MSG_ERROR_NOTFOUND = "O caminho que você está tentando acessar não existe!";
	private static final String MSG_ERROR = "Ops... Encontramos um erro na nossa aplicação! Check os logs do servidor para verificar o que está acontecendo.";

	public Reflection(HttpServletRequest req, HttpServletResponse resp) {
		this.request = req;
		this.response = resp;
	}

	private HttpServletRequest getHttpRequest() {
		return this.request;
	}

	private HttpServletResponse getHttpResponse() {
		return this.response;
	}

	private void setCurrentRequestObj(CurrentRequest currentRequestObj) {
		this.currentRequestObj = currentRequestObj;
	}

	private CurrentRequest getCurrentRequestObj() {
		return currentRequestObj;
	}

	/**
	 * 
	 * @return
	 * @throws BibliotecaException
	 */
	private AbstractController getController() throws BibliotecaException {

		String strController = getCurrentRequestObj().getControlller();
		String fullQualifiedNameController = "br.com.biblioteca.controller."
				+ BibliotecaHelper.toCamelCase(strController) + "Controller";

		try {

			Class<?> clazz = Class.forName(fullQualifiedNameController);
			return (AbstractController) clazz.newInstance();

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new BibliotecaException(MSG_ERROR_NOTFOUND);
		}

	}

	/**
	 * 
	 * @return
	 * @throws BibliotecaException
	 */
	private Method getMethod() throws BibliotecaException {

		controller = this.getController();
		controller.setRequestAndResponse(getHttpRequest(), getHttpResponse());

		String strMethod = getCurrentRequestObj().getAction();

		try {
			return controller.getClass().getMethod(strMethod);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new BibliotecaException(MSG_ERROR_NOTFOUND);
		}

	}

	/**
	 * 
	 * @throws BibliotecaException
	 */
	public void invokeAction() throws BibliotecaException {

		try {

			setCurrentRequestObj((CurrentRequest) getHttpRequest().getAttribute("currentRequestObj"));

			method = this.getMethod();
			method.invoke(controller);

			if (controller.isForward()) {

				RequestDispatcher dispatcher = getHttpRequest().getRequestDispatcher(controller.getForwardFile());
				dispatcher.forward(getHttpRequest(), getHttpResponse());

			} else {

				getHttpResponse()
						.sendRedirect("/" + getCurrentRequestObj().getContextRoot() + "/" + controller.getRedirectURI());

			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new BibliotecaException(MSG_ERROR_NOTFOUND);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			throw new BibliotecaException(MSG_ERROR);
		}

	}

}
