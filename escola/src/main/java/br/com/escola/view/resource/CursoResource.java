package br.com.escola.view.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.CursoController;
import br.com.escola.model.repository.CursoRepository;
import br.com.escola.view.dto.CursoDTO;

@RestController
@RequestMapping("/curso")
public class CursoResource {
	
	@Autowired
	private CursoRepository repository;

	@GetMapping
	public List<CursoDTO> buscar() {
		return new CursoController(repository).buscar();
	}

	@GetMapping("/{id}")
	public CursoDTO buscar(@PathVariable Integer id) {
		return new CursoController(repository).buscar(id);
	}

	@PostMapping
	public void inserir(@RequestBody CursoDTO pessoa) {
		new CursoController(repository).persistir(pessoa);
	}

	@PutMapping
	public CursoDTO atualizar(@RequestBody CursoDTO pessoa) {
		return new CursoController(repository).persistir(pessoa);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Integer id) {
		repository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}


}
