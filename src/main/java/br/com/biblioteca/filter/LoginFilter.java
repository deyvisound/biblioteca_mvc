package br.com.biblioteca.filter;

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
import javax.servlet.http.HttpSession;

import br.com.biblioteca.arq.DateBaseDriver;
import br.com.biblioteca.arq.CurrentRequest;

/**
 * Servlet Filter implementation class AppLoginFilter
 */
public class LoginFilter implements Filter {

	private String[] urlPermitidaArray;
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			urlPermitidaArray = fConfig.getInitParameter("urlPublica").split(",");
			DateBaseDriver.getInstance().popularBanco();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession(false);
		String loginPath = request.getContextPath() + "/login";		

		boolean loggedIn = session != null && session.getAttribute("usuarioLogado") != null;
		boolean loginRequest = (request.getRequestURI().indexOf(loginPath) >= 0);
		
		CurrentRequest currentRequest = new CurrentRequest(request.getRequestURI(), urlPermitidaArray);		

		if (loggedIn || loginRequest || currentRequest.isAllowedAccess() ) {
			req.setAttribute("currentRequestObj", currentRequest);
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(loginPath);
		}

	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
