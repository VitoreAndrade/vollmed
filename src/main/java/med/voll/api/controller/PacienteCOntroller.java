package med.voll.api.controller;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("paciente")
public class PacienteCOntroller {
    @Autowired
    private PacienteService service;


    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid dadosCadastrosPacientes dados){
        service.cadastrar(dados);
    }


    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.listar(paginacao);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid dadosAtualizacaoPacientesDto dadosAtualizadosPacientes){
        service.atualizarInformacoesPaciente(dadosAtualizadosPacientes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id){
        service.remover(id);
    }

}
