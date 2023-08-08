package med.voll.api.service;

import med.voll.api.dto.DadosAtualizacaoConsultorioDto;
import med.voll.api.dto.DadosCadastroConsultorioDto;
import med.voll.api.dto.DadosListagemConsultorio;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.model.Consultorio;
import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;
import med.voll.api.repositorio.ConsultorioRepository;
import med.voll.api.repositorio.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultorioService {
    @Autowired
    private ConsultorioRepository repository;

    public void cadastarConsultorio (DadosCadastroConsultorioDto dados){
        repository.save(new Consultorio(dados));
    }

    public void atualizarDadosConsultorio (DadosAtualizacaoConsultorioDto dados){
        Consultorio consultorio = repository.findById(dados.id()).get();

        if(dados.nome_consultorio() != null){
            consultorio.setNome_consultorio(dados.nome_consultorio());
        }
        if(dados.especialidade() != null){
            consultorio.setEspecialidade(dados.especialidade());
        }
        if(dados.endereco() != null){
            consultorio.getEndereco().atualizarInformacoes(dados.endereco());
        }
    }
    public Page<DadosListagemConsultorio> listarConsultorio(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemConsultorio::new);
    }
}
