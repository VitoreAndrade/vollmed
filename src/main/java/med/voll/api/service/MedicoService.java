package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.DadosAtualizacaoMedicosDto;
import med.voll.api.dto.DadosDetalhamentoMedico;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.dto.dadosCadastroMedicosDto;
import med.voll.api.model.Consultorio;
import med.voll.api.model.Medico;
import med.voll.api.repositorio.EnderecoRepository;
import med.voll.api.repositorio.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;


    public ResponseEntity cadastrar(dadosCadastroMedicosDto dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        var cadastro =repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

            public ResponseEntity atualizarDados(DadosAtualizacaoMedicosDto dadosAtualizadosMedicos) {

                Medico medico = repository.findById(dadosAtualizadosMedicos.id()).get();


                if(dadosAtualizadosMedicos.nome() != null){
                    medico.setNome(dadosAtualizadosMedicos.nome());
                }
                if(dadosAtualizadosMedicos.telefone() != null) {
                    medico.setTelefone( dadosAtualizadosMedicos.telefone());
                }
                if (dadosAtualizadosMedicos.endereco() != null){
                    medico.getEndereco().atualizarInformacoes(dadosAtualizadosMedicos.endereco());
                }

                var medicoAtualizado = repository.saveAndFlush(medico);
                return ResponseEntity.ok(new DadosDetalhamentoMedico(medicoAtualizado));
            }

    public ResponseEntity<Page <DadosListagemMedico>> listar(Pageable paginacao) {
      var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }


    public ResponseEntity excluir (Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();

    }
    public ResponseEntity detalhamento (Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

}
