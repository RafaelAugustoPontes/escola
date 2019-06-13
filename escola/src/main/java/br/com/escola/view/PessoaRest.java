package br.com.escola.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.PessoaBO;
import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.view.dto.PessoaDTO;

@RestController
@RequestMapping("/pessoa")
public class PessoaRest {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<PessoaDTO> buscarPessoas() {
		return new PessoaBO(pessoaRepository).buscarUsuarios();
	}

	@GetMapping("/{id}")
	public PessoaDTO buscarPessoa(@PathVariable Integer id) {
		return new PessoaBO(pessoaRepository).buscarUsuario(id);
	}

	@PostMapping
	public void cadastrarPessoa(@RequestBody PessoaDTO pessoa) {
		new PessoaBO(pessoaRepository).cadastrarPessoa(pessoa);
	}

}
