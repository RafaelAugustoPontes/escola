package br.com.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.model.UsuarioModel;
import br.com.escola.repository.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping("/usuarios")
	public List<UsuarioModel> buscarUsuarios() {
		return repository.findAll();
	}

}
