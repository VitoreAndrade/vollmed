package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Agendamento;
import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

import java.util.List;

public record DadosCadastroConsultorioDto(
        @NotBlank
        String nome_consultorio,
//        @NotNull
//        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco,
        List<Long> medicos,
        List<Long> especialidades,
        List<Long> pacientes,
        List<Long> agendamentos
        ) {
}
