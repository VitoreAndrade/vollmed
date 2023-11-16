package med.voll.api.service;

import med.voll.api.dt.DadosAgendamentoConsulta;
import med.voll.api.dto.DadosCancelamentoConsulta;
import med.voll.api.dto.DadosDetalhamentoConsulta;
import med.voll.api.inf.ValidacaoException;
import med.voll.api.model.Consulta;
import med.voll.api.model.Medico;
import med.voll.api.repositorio.ConsultaRepository;
import med.voll.api.repositorio.MedicoRepository;
import med.voll.api.repositorio.PacienteRepository;
import med.voll.api.repositorio.ValidadorAgendamentoDeConsultasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultasService {

    //    @Autowired
//    private Consulta consulta;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepositoryi;
    @Autowired
    private List<ValidadorAgendamentoDeConsultasRepository> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepositoryi.existsById(dados.idPaciente())) {
            throw new ValidacaoException("id do paciente informado não existe");
        }
        if (dados.idMedico() != null & !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("id do médico informado não existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepositoryi.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade obrigatoria quando o médico não for escolhido");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informada não existe");
        }
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
