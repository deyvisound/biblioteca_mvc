package br.com.biblioteca.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.biblioteca.BibliotecaHelper;
import br.com.biblioteca.controller.AbstractController;

/**
 * @author deyvison
 */
@WebServlet("/biblioteca")
public class ApplicationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String context;
	private String controller;
	private String action;
	private String entityId;

	private static final String PACKAGE_CONTROLLER = "br.com.biblioteca.controller";
	private Class<?> myClass;
	private AbstractController abstractController = null;
	private java.lang.reflect.Method method;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		context = (String) req.getAttribute(AppEnum.CONTEXT_ROOT.toString());
		controller = (String) req.getAttribute(AppEnum.CONTROLLER.toString());
		action = (String) req.getAttribute(AppEnum.ACTION.toString());
		entityId = (String) req.getAttribute(AppEnum.ENTITY_ID.toString());

		if (context != null && controller != null && !controller.equals("public") ) {
			try {
				myClass = Class.forName(getClassFullQualifiedName(controller));
				abstractController = (AbstractController) myClass.newInstance();
				abstractController.setRequestAndResponse(req, resp);

				if (BibliotecaHelper.isNotEmpty(action)) {

					if (BibliotecaHelper.isNotEmpty(entityId)) {
						// TODO: à fazer
					}

					method = abstractController.getClass().getMethod(action);
					method.invoke(abstractController);

				}

				if (abstractController != null && abstractController.isForward()) {
					RequestDispatcher dispatcher = req.getRequestDispatcher(getDestinoForward());
					dispatcher.forward(req, resp);
				} else if (abstractController != null && abstractController.isRedirect()) {
					resp.sendRedirect(getPathRedirect());
				}

			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
					| SecurityException | IllegalArgumentException | InvocationTargetException e) {

				e.printStackTrace();
				resp.sendRedirect(getErroPage());

			}
		} else if(controller != null && controller.equals("public")) {
			// TODO : nothing
		} else {
			resp.sendRedirect("/");
		}

	}

	/**
	 * 
	 * @return
	 */
	private String getErroPage() {
		return "/public/error/notfound.jsp";
	}

	/**
	 * Busca a JSP a qual a requisição será encaminhada
	 * 
	 * @return
	 */
	private String getDestinoForward() {
		return "/WEB-INF/" + controller.toLowerCase() + "/" + abstractController.getJspDestino();
	}

	/**
	 * Busca o caminho do redirecionamento
	 * @return
	 */
	private String getPathRedirect() {
		return "/" + this.context + "/" + controller + "/" + abstractController.getJspDestino();
	}

	/**
	 * 
	 */
	private String getClassFullQualifiedName(String controller) {
		return ApplicationServlet.PACKAGE_CONTROLLER + "." + BibliotecaHelper.wordToCamelCase(controller)
				+ "Controller";
	}

}
