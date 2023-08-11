package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastrosEspecialidadesDto(
        @NotBlank
        String nomeEspecialidade) {
}
