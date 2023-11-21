package med.voll.api.validacoes.agendamento;

import med.voll.api.dt.DadosAgendamentoConsulta;
import med.voll.api.infra.ValidacaoException;
import med.voll.api.repositorio.ValidadorAgendamentoDeConsultasRepository;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultasRepository {
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        //para verificar se vai cair em um domingo
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour()<7;
        var depoisDoEncerramentDaClinica = dataConsulta.getHour() >18;

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentDaClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }

    }

}
