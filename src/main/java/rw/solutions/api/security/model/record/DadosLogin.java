package rw.solutions.api.security.model.record;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(@NotBlank
						 String username,
						 @NotBlank
						 String password) {

}
