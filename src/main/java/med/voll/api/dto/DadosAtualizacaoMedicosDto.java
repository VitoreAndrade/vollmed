package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedicosDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
