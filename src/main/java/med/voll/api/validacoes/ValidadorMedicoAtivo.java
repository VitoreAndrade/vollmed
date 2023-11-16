package med.voll.api.validacoes;

import med.voll.api.dt.DadosAgendamentoConsulta;
import med.voll.api.inf.ValidacaoException;
import med.voll.api.repositorio.MedicoRepository;
import med.voll.api.repositorio.ValidadorAgendamentoDeConsultasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultasRepository {
    @Autowired
    private MedicoRepository repository;

    public void validar (DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }

}
