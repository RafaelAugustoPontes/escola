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

import br.com.escola.controller.PessoaController;
import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.view.dto.PessoaDTO;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<PessoaDTO> buscar() {
		return new PessoaController(pessoaRepository).buscar();
	}

	@GetMapping("/{id}")
	public PessoaDTO buscar(@PathVariable Integer id) {
		return new PessoaController(pessoaRepository).buscar(id);
	}

	@GetMapping("/matricula")
	public Integer buscarMatricula() {
		return new PessoaController(pessoaRepository).buscarMatricula();
	}

	@PostMapping
	public void inserir(@RequestBody PessoaDTO pessoa) {
		new PessoaController(pessoaRepository).persistir(pessoa);
	}

	@PutMapping
	public PessoaDTO atualizar(@RequestBody PessoaDTO pessoa) {
		return new PessoaController(pessoaRepository).persistir(pessoa);
	}

}
