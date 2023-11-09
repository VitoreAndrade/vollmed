package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.Endereco;
import med.voll.api.model.Paciente;

public record  DadosDetalhamentoPaciente (
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf(),
                paciente.getEndereco());
    }
}
