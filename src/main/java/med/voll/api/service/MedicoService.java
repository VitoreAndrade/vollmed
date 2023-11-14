package med.voll.api.service;

import med.voll.api.dto.DadosAtualizacaoMedicosDto;
import med.voll.api.dto.DadosDetalhamentoMedico;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.dto.dadosCadastroMedicosDto;
import med.voll.api.infra.TratadorDeErros;
import med.voll.api.model.Medico;
import med.voll.api.repositorio.EnderecoRepository;
import med.voll.api.repositorio.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TratadorDeErros tratadorDeErros;


    public ResponseEntity cadastrar(dadosCadastroMedicosDto dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        var cadastro = repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    public ResponseEntity atualizarDados(DadosAtualizacaoMedicosDto dadosAtualizadosMedicos) {
        try {
            Optional<Medico> optionalMedico = repository.findById(dadosAtualizadosMedicos.id());

            if (optionalMedico.isPresent()) {
                Medico medico = optionalMedico.get();

                if (dadosAtualizadosMedicos.nome() != null) {
                    medico.setNome(dadosAtualizadosMedicos.nome());
                }
                if (dadosAtualizadosMedicos.telefone() != null) {
                    medico.setTelefone(dadosAtualizadosMedicos.telefone());
                }
                if (dadosAtualizadosMedicos.endereco() != null) {
                    medico.getEndereco().atualizarInformacoes(dadosAtualizadosMedicos.endereco());
                }

                var medicoAtualizado = repository.saveAndFlush(medico);
                return ResponseEntity.ok(new DadosDetalhamentoMedico(medicoAtualizado));
            } else {
                return ResponseEntity.badRequest().body("Médico não encontrado, confira o ID");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar atualizar os dados do médico");
        }
    }

    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }


    public ResponseEntity excluir(Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();

    }

    public ResponseEntity detalhamento(Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

}
