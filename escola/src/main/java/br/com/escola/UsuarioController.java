package br.com.escola;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/servicos")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping("/usuarios")
	public List<UsuarioModel> buscarUsuarios() {
		return repository.findAll();
	}

}
