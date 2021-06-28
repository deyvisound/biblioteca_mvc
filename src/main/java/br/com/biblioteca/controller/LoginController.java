package br.com.biblioteca.controller;

import java.util.HashMap;
import java.util.Map;

import br.com.biblioteca.modelo.Usuario;

public class LoginController extends AbstractController {
	
	public void loginForm() {		
		redirect("public/login/login.jsp");		
	}
	
	public void logout() {
		destroySessionAttribute("user");
		redirect("login");
	}

	public void logon() {

		String login = getParam("login");
		String password = getParam("password");

		Map<String, String> messages = new HashMap<String, String>();

		if (login == null || login.isEmpty()) {
			messages.put("username", "Please enter username");
		}

		if (password == null || password.isEmpty()) {
			messages.put("password", "Please enter password");
		}

		if (messages.isEmpty()) {
			UsuarioController usuarioController = new UsuarioController();
			Usuario user = usuarioController.validarLoginUsuario(login, password);

			if (user != null) {
				setSessionAttribute("user", user);
				redirect("");
				
				return;
			} else {
				messages.put("login", "Unknown login, please try again");
			}
		}
		
		redirect("login");
	}

}
