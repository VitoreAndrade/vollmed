package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Consultorio;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public record DadosCadastroAgendamentoDto(
        @NotNull
        Long medico,
        @NotNull
        Long paciente,
        @NotNull
        @DateTimeFormat(pattern = "dd/mm/yyyy")
        String data_agendamento,
        @NotNull
        @DateTimeFormat(pattern = "HH:mm")
        String hora,
        @NotNull
        Long consultorios

) {
}
