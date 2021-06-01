package br.edu.ifpb.projeto.vacinacao.model;

public enum TipoUsuario {
	USUARIO("Usuário"),
	ADMIN("ADMINISTRADOR");
	
	
	private final String tipoUsuario;
	
	TipoUsuario(String tipoUsuario){
		this.tipoUsuario = tipoUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}
}
