package med.voll.api.dto;

import med.voll.api.model.Endereco;
import med.voll.api.model.Paciente;

public record DadosDetalhamentoPacienteDto(

        Long id,
        String nome,
        String email,
        String cpf,
        Endereco endereco,
        String telefone){
    public DadosDetalhamentoPacienteDto(Paciente paciente){
        this(paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getEndereco(),
                paciente.getTelefone());
    }
}
