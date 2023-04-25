package rw.solutions.api.model.record;

import jakarta.validation.constraints.NotBlank;

public record CadastroPessoa(@NotBlank
							 String nome,
							 @NotBlank
							 String descricao, 
							 @NotBlank
							 String cargo, 
							 @NotBlank
							 String urlLinkdin) {
}
