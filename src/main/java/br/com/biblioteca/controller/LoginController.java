package br.com.biblioteca.controller;

public class LoginController extends AbstractController {
	
	public void loginForm() {		
		redirect("public/login/login.jsp");		
	}
	
	public void logout() {
		destroySessionAttribute("usuarioLogado");
		redirect("login");
	}

	public void logon() {

		String login = getParam("login");
		String password = getParam("password");

		if (login == null || login.isEmpty()) {
			addErrorMenssage("Please enter username");
		}

		if (password == null || password.isEmpty()) {
			addErrorMenssage("Please enter password");
		}

		if (!hasErrors()) {
			UsuarioController usuarioController = new UsuarioController();

			if (usuarioController.isUsuarioValido(login, password)) {
				setSessionAttribute("usuarioLogado", usuarioController.buscarPorLoginSenha(login, password));
				redirect("");
				
				return;
			} else {
				addErrorMenssage("Unknown login, please try again");
			}
		}
		
		redirect("login");
	}

}
