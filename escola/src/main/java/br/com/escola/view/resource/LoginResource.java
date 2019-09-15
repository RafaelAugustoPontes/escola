package br.com.escola.view.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.view.dto.UsuarioDTO;

@RestController
@RequestMapping("/login")
public class LoginResource {

	@PostMapping
	public void teste(@RequestBody UsuarioDTO credentials) {
		System.out.println(credentials.getUsername() + " - " + credentials.getPassword());
	}

}
