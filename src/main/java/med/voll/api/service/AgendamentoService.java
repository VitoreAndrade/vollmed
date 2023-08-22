package med.voll.api.service;

import med.voll.api.dto.DadosCadastroAgendamentoDto;
import med.voll.api.model.*;
import med.voll.api.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;
    @Autowired
    private ConsultorioRepository consultorioRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;



public void cadastrarAgendamneto(DadosCadastroAgendamentoDto agenda){


    Consultorio consulta = consultorioRepository.findById(agenda.consultorios()).orElse(null);

    Medico medicos = medicoRepository.findById(agenda.medico()).orElse(null);

    Paciente pacientes = pacienteRepository.findById(agenda.paciente()).orElse(null);

    Especialidade especialidade = especialidadeRepository.findById(agenda.id_especialidades()).orElse(null);

    var data = agenda.dataHoraAgendamento();
    var index =0;

// if(medicos.getId_especialidade() == especialidade.getId()){

        repository.saveAndFlush(new Agendamento(consulta, medicos, pacientes, data,especialidade));


    }
}




