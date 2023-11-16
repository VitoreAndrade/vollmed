package med.voll.api.dt;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta (
        @NotNull
        Long idPaciente,
        @NotNull
        Long idMedico,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade
){
}
