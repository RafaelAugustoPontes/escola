package br.com.escola.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.entidades.UsuarioModel;


public interface UsuarioRepository extends JpaRepository<UsuarioModel,Integer> {

}
