package br.com.escola.view.dto;

public class UsuarioDTO {

	private String username;
	private String senhaAtual;
	private String novaSenha;
	private String repeticaoNovaSenha;

	public UsuarioDTO() {

	}

	public UsuarioDTO(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getRepeticaoNovaSenha() {
		return repeticaoNovaSenha;
	}

	public void setRepeticaoNovaSenha(String repeticaoNovaSenha) {
		this.repeticaoNovaSenha = repeticaoNovaSenha;
	}

}
