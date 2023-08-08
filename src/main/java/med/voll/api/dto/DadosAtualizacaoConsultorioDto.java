package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Especialidade;

import java.util.List;

public record DadosAtualizacaoConsultorioDto(
        @NotNull
        Long id,
        String nome_consultorio,
        Especialidade especialidade,
        DadosEndereco endereco,
        List<Long> medicos
){

}
