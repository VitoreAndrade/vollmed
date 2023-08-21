package med.voll.api.service;

import med.voll.api.dto.DadosCadastroAgendamentoDto;
import med.voll.api.model.Agendamento;
import med.voll.api.model.Consultorio;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import med.voll.api.repositorio.AgendamentoRepository;
import med.voll.api.repositorio.ConsultorioRepository;
import med.voll.api.repositorio.MedicoRepository;
import med.voll.api.repositorio.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public void cadastrarAgendamento(Long consultorio, Long medico, Long paciente) {


        Consultorio consulta = consultorioRepository.findById(consultorio).orElse(null);
        Medico medicos = medicoRepository.findById(medico).orElse(null);
        Paciente pacientes = pacienteRepository.findById(paciente).orElse(null);

            consulta.getMedicos().add(medicos);
            consulta.getPacientes().add(pacientes);
            medicos.getConsultorios().add(consulta);

           repository.saveAndFlush(new Agendamento(consulta,medicos,pacientes));


    }
}


