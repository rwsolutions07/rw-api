package rw.solutions.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.solutions.api.model.Pessoa;

public interface PessoaRepository  extends JpaRepository<Pessoa, Long> {

}
