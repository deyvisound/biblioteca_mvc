package br.com.biblioteca.arq;

import br.com.biblioteca.utils.BibliotecaHelper;

/**
 * 
 * @author deyvison
 */
public class CurrentRequest {

	private final int CONTEXT_ROOT = 1;
	private final int CONTROLLER = 2;
	private final int ACTION = 3;
	private final int ENTITY_ID = 4;

	private String contextRoot;
	private String controlller;
	private String action;
	private String entityId;

	private String[] urlPermitidaArray;
	private boolean allowedAccess;

	public CurrentRequest(String uri, String[] urlPermitidaArray) {
		this.urlPermitidaArray = urlPermitidaArray;

		buildRequest(uri);
	}

	/**
	 * Contrói o objeto de requisição
	 * 
	 * @param uri
	 */
	private void buildRequest(String uri) {

		String[] uriArray = tratarParametrosGet(uri).split("/");

		checaAcessoPermitido(uriArray);

		if (isAllowedAccess())
			this.contextRoot = "biblioteca";
		else
			fillMembersURI(uriArray);
	}

	/**
	 * Trata os parâmetros presentes na URI antes de seccioná-la em partes
	 * 
	 * @param uri
	 * @return
	 */
	private String tratarParametrosGet(String uri) {
		String uriBase = uri;
		@SuppressWarnings("unused") // TODO:
		String session;
		@SuppressWarnings("unused") // TODO:
		String queryString;

		String[] arrayUri;

		if (uri.contains("?")) {
			arrayUri = uri.split("\\?");
			uriBase = arrayUri[0];
			queryString = arrayUri[1];
		} else if (uri.contains(";")) {
			arrayUri = uri.split(";");
			uriBase = arrayUri[0];
			session = arrayUri[1];
		}

		return uriBase;
	}

	/**
	 * Popula os membros da requisição
	 * 
	 * @param uriArray
	 */
	private void fillMembersURI(String[] uriArray) {
		for (int i = 1; i < uriArray.length; i++) {
			switch (i) {
			case CONTEXT_ROOT:
				this.contextRoot = uriArray[i];
				break;

			case CONTROLLER:
				this.controlller = uriArray[i];
				break;

			case ACTION:
				this.action = uriArray[i];
				break;

			case ENTITY_ID:
				this.entityId = uriArray[i];
				break;
			}
		}

		if ("login".equals(this.controlller) && BibliotecaHelper.isEmpty(this.action))
			this.action = "loginForm";
	}

	/**
	 * Verifica se o usuário está acesso alguma URL da qual não precise passar pelo
	 * fluxo comum.
	 * 
	 * @param uriArray
	 */
	private void checaAcessoPermitido(String[] uriArray) {
		if (urlPermitidaArray != null)
			for (int i = 1; i < uriArray.length; i++)
				for (int j = 0; j < urlPermitidaArray.length; j++)
					if (urlPermitidaArray[j].trim().equals(uriArray[i].trim()))
						this.allowedAccess = true;
	}

	public String getContextRoot() {
		return contextRoot;
	}

	public String getControlller() {
		return controlller;
	}

	public String getAction() {
		return action;
	}

	public Integer getEntityId() {
		if (entityId != null)
			return Integer.valueOf(entityId);

		return null;
	}

	public boolean isAllowedAccess() {
		return allowedAccess;
	}

}
