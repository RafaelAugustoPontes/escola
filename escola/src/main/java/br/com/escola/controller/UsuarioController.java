package br.com.escola.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.escola.model.entidades.UsuarioModel;
import br.com.escola.model.repository.UsuarioRepository;
import br.com.escola.view.dto.UsuarioDTO;

public class UsuarioController {

	private UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void alterarSenha(UsuarioDTO usuarioDTO, String usuarioLogado) {
		UsuarioModel usuarioModel = usuarioRepository.findByLogin(usuarioLogado);
		if (usuarioModel == null)
			throw new IllegalArgumentException("Usuário null");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (!encoder.matches(usuarioDTO.getSenhaAtual(), usuarioModel.getSenha()))
			throw new IllegalArgumentException("Senha atual incorreta.");

		if (!usuarioDTO.getNovaSenha().equals(usuarioDTO.getRepeticaoNovaSenha()))
			throw new IllegalArgumentException("Senhas não conferem.");

		usuarioModel.setSenha(encoder.encode(usuarioDTO.getNovaSenha()));
		usuarioRepository.save(usuarioModel);
	}

}
