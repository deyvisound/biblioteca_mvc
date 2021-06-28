package br.com.biblioteca.arq;

import br.com.biblioteca.utils.BibliotecaHelper;

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

	private void buildRequest(String uri) {

		String[] uriArray = uri.split("/");

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
		
		if("login".equals(this.controlller) && BibliotecaHelper.isEmpty(this.action))
			this.action = "loginForm";
		
		if(urlPermitidaArray != null)
			for (int i = 1; i < uriArray.length; i++)
				for (int j = 0; j < urlPermitidaArray.length; j++)
					if(urlPermitidaArray[j].trim().equals(uriArray[i].trim()))
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
		if(entityId != null)
			return Integer.valueOf(entityId);
					
		return null;
	}

	public boolean isAllowedAccess() {
		return allowedAccess;
	}
	


}
