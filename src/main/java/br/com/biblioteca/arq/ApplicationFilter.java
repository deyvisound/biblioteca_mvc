package br.com.biblioteca.arq;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.utils.BibliotecaHelper;

public class ApplicationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		CurrentRequest currentRequest = (CurrentRequest) req.getAttribute("currentRequestObj");

		if (currentRequest.isAllowedAccess()) {
			chain.doFilter(request, response);
		} else {

			try {

				if (BibliotecaHelper.isEmpty(currentRequest.getControlller())) {
					resp.sendRedirect("/biblioteca/index.jsp");
				} else {
					Reflection rfct = new Reflection(req, resp);
					rfct.invokeAction();
				}

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
