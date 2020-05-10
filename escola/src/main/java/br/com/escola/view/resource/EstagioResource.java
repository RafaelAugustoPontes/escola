package br.com.escola.view.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.EstagioController;
import br.com.escola.model.repository.EstagioRepository;
import br.com.escola.view.dto.EstagioDTO;

@RestController
@RequestMapping("/estagio")
public class EstagioResource {

	@Autowired
	private EstagioRepository repository;

	@GetMapping
	public List<EstagioDTO> buscar() {
		return new EstagioController(repository).buscar();
	}

	@GetMapping("/{id}")
	public EstagioDTO buscar(@PathVariable Integer id) {
		return new EstagioController(repository).buscar(id);
	}

	@PostMapping
	public void inserir(@RequestBody EstagioDTO pessoa) {
		new EstagioController(repository).persistir(pessoa);
	}

	@PutMapping
	public EstagioDTO atualizar(@RequestBody EstagioDTO pessoa) {
		return new EstagioController(repository).persistir(pessoa);
	}
	
	
	@PutMapping("/arquivar/{idEstagio}")
	public EstagioDTO arqvuiar(@PathVariable Integer idEstagio) {
		return new EstagioController(repository).arquivar(idEstagio);
	}
	
	


}
