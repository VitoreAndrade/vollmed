package med.voll.api.validacoes.agendamento;

import med.voll.api.dt.DadosAgendamentoConsulta;
import med.voll.api.infra.ValidacaoException;
import med.voll.api.repositorio.ConsultaRepository;
import med.voll.api.repositorio.ValidadorAgendamentoDeConsultasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultasRepository {
    @Autowired
    private ConsultaRepository repository;

    public void validar (DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico já possui outra consulta nesse mesmo horário");
        }

    }
}

