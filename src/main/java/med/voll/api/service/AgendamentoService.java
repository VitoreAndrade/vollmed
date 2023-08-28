package med.voll.api.service;


import med.voll.api.controller.DadosErro;
import med.voll.api.controller.RespostaRequisicao;
import med.voll.api.dto.DadosCadastroAgendamentoDto;
import med.voll.api.model.*;
import med.voll.api.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import org.springframework.http.HttpStatus;

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


    public  ResponseEntity<RespostaRequisicao> cadastrarAgendamneto(DadosCadastroAgendamentoDto agenda) throws DadosErro {


        Consultorio consulta = consultorioRepository.findById(agenda.consultorios()).orElse(null);

        Medico medicos = medicoRepository.findById(agenda.medico()).orElse(null);

        Paciente pacientes = pacienteRepository.findById(agenda.paciente()).orElse(null);

        Especialidade especialidade = especialidadeRepository.findById(agenda.especialidades()).orElse(null);

        var data = agenda.dataHoraAgendamento();







//            if (medicos.getId_especialidade() == consulta.getId_especialidade()) {
//                repository.saveAndFlush(new Agendamento(consulta, medicos, pacientes, data, especialidade));
//            }
        try {
            if (medicos.getId_especialidade() == consulta.getId_especialidade() && especialidade.getId() == consulta.getId_especialidade()) {

                repository.saveAndFlush(new Agendamento(consulta, medicos, pacientes, data,especialidade));
            }
            else{
                throw new DadosErro("Erro no agendamento de dados: Cadastros incoerentes");
            }
        }
        catch (DadosErro erro){
            System.out.println("Erro de dados viu");

            RespostaRequisicao dc = new RespostaRequisicao(  "medico não corresponde ao consultorio" + erro.getMessage(), HttpStatus.BAD_REQUEST.toString());


            return new ResponseEntity<>(dc, HttpStatus.BAD_REQUEST);

//            return dc;

            //return "Medico não corresponde ao consultorio";
        }

        catch(Exception ex){
            System.out.println("Erro ao salvar o agendamento" + ex.getMessage());
        }
        RespostaRequisicao dc = new RespostaRequisicao(  "Agendamento concluído", HttpStatus.OK.toString());

        return new ResponseEntity<>(dc, HttpStatus.OK);
    }


//                System.err.println("erro" + ex.getMessage());
//
//            }


//        if (medicos.getId_especialidade() == consulta.getId_especialidade() ) {

//            repository.saveAndFlush(new Agendamento(consulta, medicos, pacientes, data, especialidade));

}






