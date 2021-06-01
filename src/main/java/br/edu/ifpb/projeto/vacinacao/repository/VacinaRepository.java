package br.edu.ifpb.projeto.vacinacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifpb.projeto.vacinacao.model.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

	@Query("select sum(totalDoses) from Vacina")
	int somaTotalDoses();
}
