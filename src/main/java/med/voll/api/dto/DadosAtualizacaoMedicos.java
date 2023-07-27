package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.DadosEndereco;

public record DadosAtualizacaoMedicos(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
