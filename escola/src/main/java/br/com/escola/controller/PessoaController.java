package br.com.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.model.PessoaModel;
import br.com.escola.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController extends BaseController {

	@Autowired
	private PessoaRepository repository;

	@GetMapping
	public List<PessoaModel> buscarPessoas() {
		return repository.findAll();
	}

}
