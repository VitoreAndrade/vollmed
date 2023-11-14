package med.voll.api.service;

import med.voll.api.dto.DadosDetalhamentoPaciente;
import med.voll.api.dto.DadosListagemPaciente;
import med.voll.api.dto.dadosAtualizacaoPacientesDto;
import med.voll.api.dto.dadosCadastrosPacientes;
import med.voll.api.model.Paciente;
import med.voll.api.repositorio.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public ResponseEntity cadastrar(dadosCadastrosPacientes dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    public ResponseEntity atualizarInformacoesPaciente(dadosAtualizacaoPacientesDto dadosAtualizadosPacientes) {
        try {
            Optional<Paciente> optionalPaciente = repository.findById(dadosAtualizadosPacientes.id());
            if (optionalPaciente.isPresent()) {

                Paciente paciente = optionalPaciente.get();

                if (dadosAtualizadosPacientes.nome() != null) {
                    paciente.setNome(dadosAtualizadosPacientes.nome());
                }

                if (dadosAtualizadosPacientes.telefone() != null) {
                    paciente.setTelefone(dadosAtualizadosPacientes.telefone());
                }
                return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
            } else {
                return ResponseEntity.badRequest().body("Paciente não encontrado, confira o ID");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar atualizar os dados do paciente");
        }
    }

    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity remover(Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity detalhar(Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}
