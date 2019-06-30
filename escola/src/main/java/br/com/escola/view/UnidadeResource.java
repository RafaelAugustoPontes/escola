package br.com.escola.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.UnidadeController;
import br.com.escola.model.repository.UnidadeRepository;
import br.com.escola.view.dto.UnidadeDTO;

@RestController
@RequestMapping("/unidade")
public class UnidadeResource {
	
	@Autowired
	private UnidadeRepository unidadeRepository;

	@GetMapping
	public List<UnidadeDTO> buscar() {
		return new UnidadeController(unidadeRepository).buscar();
	}

	@GetMapping("/{id}")
	public UnidadeDTO buscar(@PathVariable Integer id) {
		return new UnidadeController(unidadeRepository).buscar(id);
	}


	@PostMapping
	public void inserir(@RequestBody UnidadeDTO pessoa) {
		new UnidadeController(unidadeRepository).persistir(pessoa);
	}

	@PutMapping
	public UnidadeDTO atualizar(@RequestBody UnidadeDTO pessoa) {
		return new UnidadeController(unidadeRepository).persistir(pessoa);
	}

}
