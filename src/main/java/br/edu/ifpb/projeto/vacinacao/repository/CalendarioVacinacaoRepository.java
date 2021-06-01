package br.edu.ifpb.projeto.vacinacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifpb.projeto.vacinacao.model.CalendarioVacinacao;

public interface CalendarioVacinacaoRepository extends JpaRepository<CalendarioVacinacao, Long> {

	@Query("select max(idCalendarioVacinacao) from CalendarioVacinacao")
	Long findByMaxId();
}
