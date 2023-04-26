package rw.solutions.api.model.record;

import jakarta.validation.constraints.NotNull;
import rw.solutions.api.model.Pessoa;

public record AtualizacaoPessoa(@NotNull
								Long id,
								String nome,
								String descricao, 
								String cargo, 
								String urlLinkdin) {

	public AtualizacaoPessoa(Pessoa pessoa) {
		this(pessoa.getId(), pessoa.getNome(), pessoa.getDescricao(), pessoa.getCargo(), pessoa.getUrlLinkedin());
	}

}
