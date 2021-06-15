package br.com.biblioteca.arq;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.biblioteca.exception.BibliotecaException;

public class ApplicationFilter implements Filter {

	private String context;
	private String controller;
	private String action;
	
	private String[] urlPublicaArray;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			urlPublicaArray = filterConfig.getInitParameter("urlPublica").split(",");
			(new BancoDeDados()).popularBanco();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {			

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String[] urlArray = req.getRequestURI().split("/");
		
		boolean acessoPublico = false;
		
		for(String acessando : urlArray)
			for(String urlPublica : urlPublicaArray)
				if(acessando.equals(urlPublica)) {
					acessoPublico = true;
					break;
				}
		
		if(acessoPublico) {
			chain.doFilter(request, response);
		}else {
		
			context = urlArray[1];
			controller = urlArray[2];
			action = urlArray[3];
	
			try {

				req.getSession().setAttribute(SessionParams.CONTEXT_ROOT, context);
				req.getSession().setAttribute(SessionParams.CURRENT_CONTROLLER, controller);
				req.getSession().setAttribute(SessionParams.CURRENT_ACTION, action);

				Reflection rfct = new Reflection(req, resp);
				rfct.invokeAction();

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				resp.sendRedirect("/biblioteca/index.jsp");
			} catch (BibliotecaException ex) {
				ex.printStackTrace();
				resp.sendRedirect("/biblioteca/public/error/error.jsp");
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
