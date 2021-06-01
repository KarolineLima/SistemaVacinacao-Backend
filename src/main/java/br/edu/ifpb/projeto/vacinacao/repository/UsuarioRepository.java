package br.edu.ifpb.projeto.vacinacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpb.projeto.vacinacao.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("from Usuario u where u.email = :email")
	Usuario findByLogin(@Param("email") String email);

}