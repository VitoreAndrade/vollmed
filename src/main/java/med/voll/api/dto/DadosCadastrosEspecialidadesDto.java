package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record   DadosCadastrosEspecialidadesDto(
        @NotBlank
        String nomeEspecialidade,
        List<Long> consultorios
) {
}
