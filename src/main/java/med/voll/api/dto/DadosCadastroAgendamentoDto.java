package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Consultorio;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import org.springframework.format.annotation.DateTimeFormat;

public record DadosCadastroAgendamentoDto(
        @NotNull
        Medico medico,
        @NotNull
        Paciente paciente,
        @NotNull
        Consultorio consultorio,
        @NotNull
        @DateTimeFormat(pattern = "dd/mm/yyyy")
        String data,
        @NotNull
        @DateTimeFormat(pattern = "HH:mm")
        String hora
) {
}
