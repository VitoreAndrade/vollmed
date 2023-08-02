package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

public record DadosCadastroConsultorioDto(
        @NotNull
        Medico medico,
        @NotNull
        Endereco endereco,
        @NotBlank
        String nome,
        @NotNull
        Especialidade especialidade
) {
}
