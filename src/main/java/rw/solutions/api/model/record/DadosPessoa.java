package rw.solutions.api.model.record;

import rw.solutions.api.model.Pessoa;

public record DadosPessoa(
		 Long id,
		 String nome,
		 String descricao, 
		 String cargo, 
		 String urlLinkdin) {
	
	public DadosPessoa(Pessoa pessoa){
		this(pessoa.getId(), pessoa.getNome(), pessoa.getDescricao(), pessoa.getCargo(), pessoa.getUrlLinkedin());
	}

}
