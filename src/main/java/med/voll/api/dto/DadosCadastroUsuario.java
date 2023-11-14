package med.voll.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank(message = "{login.obrigatorio}") String login,
                                   @NotBlank(message = "{senha.obrigatorio}") String senha) {
}
