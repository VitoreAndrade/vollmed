package med.voll.api.validacoes;

import med.voll.api.dt.DadosAgendamentoConsulta;
import med.voll.api.inf.ValidacaoException;
import med.voll.api.repositorio.PacienteRepository;
import med.voll.api.repositorio.ValidadorAgendamentoDeConsultasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDoPacienteAtivo  implements ValidadorAgendamentoDeConsultasRepository {
    @Autowired
    private PacienteRepository repository;
    public void validar (DadosAgendamentoConsulta dados){
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
    if(!pacienteEstaAtivo){
        throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
    }
    }
}
