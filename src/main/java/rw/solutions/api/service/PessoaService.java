package rw.solutions.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rw.solutions.api.model.Pessoa;
import rw.solutions.api.model.record.AtualizacaoPessoa;
import rw.solutions.api.model.record.CadastroPessoa;
import rw.solutions.api.model.record.DadosPessoa;
import rw.solutions.api.model.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	public Page<DadosPessoa> getPessoas(Pageable paginacao) {
		return this.repository.findAll(paginacao).map(DadosPessoa::new);
	}
	
	
	public DadosPessoa buscaPorID(Long id) {
		Pessoa findById = this.repository.getReferenceById(id);
		return new DadosPessoa(findById);
	}
	
	public AtualizacaoPessoa cdastrarPessoa(CadastroPessoa cadastro) {
		
		Pessoa pessoa = new Pessoa(cadastro);
		this.repository.save(pessoa);
		
		return new AtualizacaoPessoa(pessoa);
	}

	public AtualizacaoPessoa atualizarPessoa(AtualizacaoPessoa dados) {
		Pessoa pessoa = this.repository.getReferenceById(dados.id());
		pessoa.atualizarInformacoes(dados);
		this.repository.save(pessoa);
		
		return new AtualizacaoPessoa(pessoa);
	}


	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

		
}
