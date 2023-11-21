package med.voll.api.validacoes.agendamento;

import med.voll.api.dt.DadosAgendamentoConsulta;
import med.voll.api.infra.ValidacaoException;
import med.voll.api.repositorio.ValidadorAgendamentoDeConsultasRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioAntecendencia implements ValidadorAgendamentoDeConsultasRepository {
    public void validar (DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos <30){
            throw new ValidacaoException("Consulta deve ser agendada com antencedência minima de 30 min");
        }

    }
}
