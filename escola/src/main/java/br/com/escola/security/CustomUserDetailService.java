package br.com.escola.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.escola.model.entidades.UsuarioModel;
import br.com.escola.model.repository.UsuarioRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

	private final UsuarioRepository repository;

	@Autowired
	public CustomUserDetailService(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioModel usuario = Optional.ofNullable(repository.findByLogin(username))
				.orElseThrow(() -> new UsernameNotFoundException("Usu�rio n�o enontrado"));

		List<GrantedAuthority> createAuthorityAdmin = Collections.emptyList();

		CustomUserDetails customUserDetails = new CustomUserDetails(usuario.getLogin(), usuario.getSenha(),
				createAuthorityAdmin);
		customUserDetails.setNome(usuario.getPessoa().getNome());

		return customUserDetails;
	}

}
