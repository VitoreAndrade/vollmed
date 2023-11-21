package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Especialidade;

import java.util.List;

public record DadosCadastrosEspecialidadesDto(
        @NotNull
        Especialidades nomeEspecialidade,
        List<Long> consultorios
) {
}
