package br.com.escola.view.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.model.entidades.UsuarioModel;
import br.com.escola.model.repository.UsuarioRepository;

@RestController
public class UsuarioResource {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping("/usuarios")
	public List<UsuarioModel> buscarUsuarios() {
		return repository.findAll();
	}

}
