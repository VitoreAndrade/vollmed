package med.voll.api.paciente;

import med.voll.api.medico.Especialidade;
import med.voll.api.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public record DadosListagemPaciente(
        Long id,
        String nome,
        String email,
        String cpf) {

    public DadosListagemPaciente(Paciente paciente) {
            this(paciente.getId(),

                paciente.getNome(),

                paciente.getEmail(),

                paciente.getCpf());
    }
}



