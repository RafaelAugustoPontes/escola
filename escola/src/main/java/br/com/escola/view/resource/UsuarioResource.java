package br.com.escola.view.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.UsuarioController;
import br.com.escola.model.repository.UsuarioRepository;
import br.com.escola.view.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository repository;

	@PutMapping
	public void alterarSenha(@RequestBody UsuarioDTO usuario) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		new UsuarioController(repository).alterarSenha(usuario, currentPrincipalName);
	}

}
