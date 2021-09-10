package br.com.banco.repository;

import br.com.banco.model.Conta;
import br.com.banco.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

}
