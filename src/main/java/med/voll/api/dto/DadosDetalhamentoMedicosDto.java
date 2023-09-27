package med.voll.api.dto;

import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

public record DadosDetalhamentoMedicosDto(Long id, String nome, String email,
              String crm, Especialidade especialidade,
            Endereco endereco,
                String telefone) {

    public DadosDetalhamentoMedicosDto (Medico medico){
        this(medico.getId(),
        medico.getNome(),
        medico.getEmail(),
        medico.getCrm(),
        medico.getEspecialidade(),
        medico.getEndereco(),
        medico.getTelefone());
    }
}
