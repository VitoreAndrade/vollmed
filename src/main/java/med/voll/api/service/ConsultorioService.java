package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.DadosAtualizacaoConsultorioDto;
import med.voll.api.dto.DadosCadastroConsultorioDto;
import med.voll.api.dto.DadosListagemConsultorio;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.model.Consultorio;
import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import med.voll.api.repositorio.ConsultorioRepository;
import med.voll.api.repositorio.EnderecoRepository;
import med.voll.api.repositorio.EspecialidadeRepository;
import med.voll.api.repositorio.MedicoRepository;
import org.apache.catalina.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultorioService {
    @Autowired
    private ConsultorioRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public void cadastarConsultorio(DadosCadastroConsultorioDto dados) {
        repository.save(new Consultorio(dados));
    }


    public void atualizarDadosConsultorio(DadosAtualizacaoConsultorioDto dados) {
        Consultorio consultorio = repository.findById(dados.id_consultorio()).get();

        if (dados.nome_consultorio() != null) {
            consultorio.setNome_consultorio(dados.nome_consultorio());
        }
//        if(dados.especialidade() != null){
//            consultorio.setEspecialidade(dados.especialidade());
//        }
        if (dados.endereco() != null) {
            consultorio.getEndereco().atualizarInformacoes(dados.endereco());
        }
    }

    public Page<DadosListagemConsultorio> listarConsultorio(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemConsultorio::new);
    }


    public void exluirConsultorio(Long id) {
        var consultorio = repository.getReferenceById(id);
        consultorio.excluir();
    }

    public void excluirMedico(Long id) {
        var consultorio = repository.getReferenceById(id);
        consultorio.excluirMedico(id);
    }

    public void deleteMedico(Long consultorio, Long medico) {
        Consultorio con = repository.findById(consultorio).get();

        var index = 0;

        for (int i = 0; i < con.getMedicos().size(); i++) {
            if (con.getMedicos().get(i).getId().equals(medico)) {
                index = i;
                break;
            }
        }

        if (index != 0) {
            con.getMedicos().remove(index);
            repository.saveAndFlush(con);
        }
    }


    public void addMedico(Long consultorios, Long novoMedico) {
        Consultorio consul = repository.findById(consultorios).orElse(null);

        if (consul != null) {
            Medico medico = medicoRepository.findById(novoMedico).get();

            consul.getMedicos().add(medico);
            repository.saveAndFlush(consul);
        }

    }

    public void addEspecialidade(Long consultorios, Long especialidade) {
        Consultorio consul = repository.findById(consultorios).orElse(null);

        if (consul != null) {
            Especialidade especialidadeNova = especialidadeRepository.findById(especialidade).get();

            consul.getEspecialidades().add(especialidadeNova);
            repository.saveAndFlush(consul);
        }
    }
}


