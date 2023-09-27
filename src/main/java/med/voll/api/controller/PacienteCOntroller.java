package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.*;
import med.voll.api.model.Paciente;
import med.voll.api.repositorio.PacienteRepository;
import med.voll.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("paciente")
public class PacienteCOntroller {
    @Autowired
    private PacienteService service;
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastrosPacientes dados, UriComponentsBuilder uriBuilder){
        var paciente  = new Paciente(dados);
        pacienteRepository.save(paciente);
        var uri = uriBuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDto(paciente));

  //      service.cadastrar(dados);
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var listar = service.listar(paginacao);
        return ResponseEntity.ok(listar);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?>atualizar(@RequestBody @Valid dadosAtualizacaoPacientesDto dadosAtualizadosPacientes){
       var atualizar = service.atualizarInformacoesPaciente(dadosAtualizadosPacientes);
       return ResponseEntity.ok(new DadosDetalhamentoPacienteDto(atualizar));


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPacienteDto(paciente));
    }


}
