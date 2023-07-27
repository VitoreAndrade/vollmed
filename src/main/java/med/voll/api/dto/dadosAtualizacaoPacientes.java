package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.DadosEndereco;

public record dadosAtualizacaoPacientes (
        @NotNull
        Long id,
        String nome,
        String telefone,
       @Valid DadosEndereco endereco){

}
