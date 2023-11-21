package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosListagemPaciente;
import med.voll.api.dto.dadosAtualizacaoPacientesDto;
import med.voll.api.dto.dadosCadastrosPacientes;
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
@SecurityRequirement(name = "bearer-key")
public class PacienteCOntroller {
    @Autowired
    private PacienteService service;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid dadosCadastrosPacientes dados, UriComponentsBuilder uriBuilder){
        return service.cadastrar(dados, uriBuilder);
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.listar(paginacao);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid dadosAtualizacaoPacientesDto dadosAtualizadosPacientes){
        return service.atualizarInformacoesPaciente(dadosAtualizadosPacientes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id){
       return service.remover(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity datalhamento(@PathVariable Long id){
        return service.detalhar(id);
    }

}
