package br.com.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.bo.pessoa.PessoaBO;
import br.com.escola.bo.pessoa.dto.PessoaDTO;
import br.com.escola.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController extends BaseController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<PessoaDTO> buscarPessoas() {
		return new PessoaBO(pessoaRepository).buscarUsuarios();
	}

	@PostMapping
	public void cadastrarPessoa(@RequestBody PessoaDTO pessoa) {
		new PessoaBO(pessoaRepository).cadastrarPessoa(pessoa);
	}

}
