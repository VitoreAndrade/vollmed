package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.DadosAtualizacaoMedicosDto;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.dto.dadosCadastroMedicosDto;
import med.voll.api.model.Consultorio;
import med.voll.api.model.Medico;
import med.voll.api.repositorio.EnderecoRepository;
import med.voll.api.repositorio.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;


    public void cadastrar( dadosCadastroMedicosDto dados){

        repository.save(new Medico(dados));
    }

    public Medico atualizarDados(DadosAtualizacaoMedicosDto dadosAtualizadosMedicos) {

        Medico medico = repository.findById(dadosAtualizadosMedicos.id()).get();

        if(dadosAtualizadosMedicos.nome() != null){
            medico.setNome(dadosAtualizadosMedicos.nome());
        };
        if(dadosAtualizadosMedicos.telefone() != null) {
            medico.setTelefone( dadosAtualizadosMedicos.telefone());
        }
        if (dadosAtualizadosMedicos.endereco() != null){
            medico.getEndereco().atualizarInformacoes(dadosAtualizadosMedicos.endereco());
        }

        return repository.saveAndFlush(medico);
    }

    public Page<DadosListagemMedico> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }


    public void excluir (Long id){
        var medico = repository.getReferenceById(id);

        medico.excluir();

    }

}
