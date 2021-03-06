package com.algaworks.algamoney.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//Se não encontrar não precisar verificar se é null
	public Optional<Usuario> findByEmail(String email);

}
