package rw.solutions.api.model.record;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoPessoa(@NotNull
								Long id,
								String nome,
								String descricao, 
								String cargo, 
								String urlLinkdin) {

}
