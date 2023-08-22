package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Consultorio;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public record DadosCadastroAgendamentoDto(
        @NotNull
        Long medico,
        @NotNull
        Long paciente,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataHoraAgendamento,
        @NotNull
        Long consultorios,
        @NotNull
        Long id_especialidades

) {
}
