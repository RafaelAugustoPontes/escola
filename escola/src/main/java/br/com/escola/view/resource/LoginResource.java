package br.com.escola.view.resource;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.UsuarioController;
import br.com.escola.model.repository.UsuarioRepository;
import br.com.escola.view.dto.UsuarioDTO;

@RestController
@RequestMapping("/login")
public class LoginResource {

	@Autowired
	private UsuarioRepository repository;

	@PostMapping
	public void login() {
	}

	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	@ResponseBody
	public UsuarioDTO getDadosLogin(Principal principal) {
		return new UsuarioController(repository).dadosLogin(principal.getName());
	}

	@PutMapping("/reset-senha")
	public void resetSenha(@RequestBody UsuarioDTO usuario) {
		new UsuarioController(repository).resetarSenha(usuario);
	}

}
