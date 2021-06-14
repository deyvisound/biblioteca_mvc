package br.com.biblioteca.servlets;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.biblioteca.BancoDeDados;
import br.com.biblioteca.servlets.AppEnum;

@WebFilter("*")
public class ApplicationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			(new BancoDeDados()).popularBanco();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();

		if (uri != null) {
			String[] fullPath = uri.split("/");
			
			if (fullPath.length > 0) {
				try {
					
					//preencher
					req.setAttribute(AppEnum.CONTEXT_ROOT.toString(), fullPath[1]);
					req.setAttribute(AppEnum.CONTROLLER.toString(), fullPath[2]);
					req.setAttribute(AppEnum.ACTION.toString(), fullPath[3]);
					req.setAttribute(AppEnum.ENTITY_ID.toString(), fullPath[4]);
					
				} catch (IndexOutOfBoundsException ex) {
					// tryCatch calaboca
				}
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
