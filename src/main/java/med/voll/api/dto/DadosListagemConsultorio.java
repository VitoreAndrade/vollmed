package med.voll.api.dto;

import med.voll.api.model.Consultorio;
import med.voll.api.model.Especialidade;

public record DadosListagemConsultorio(
        String nome_consultorio
        ) {

    public DadosListagemConsultorio (Consultorio consultorio){
        this(consultorio.getNome_consultorio());
//                consultorio.getEspecialidade());
    }
}
