package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.model.PessoaModel;

@RestController
@RequestMapping("/pessoas")
public class PessoaController extends BaseController {

	@GetMapping
	public List<PessoaModel> buscarPessoas() {
		return new ArrayList<PessoaModel>();
	}

}
