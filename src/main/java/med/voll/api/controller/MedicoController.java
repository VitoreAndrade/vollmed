package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.Medico;
import med.voll.api.dto.dadosCadastroMedicos;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.dto.DadosAtualizacaoMedicos;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;


    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid dadosCadastroMedicos dados){
        service.cadastrar(dados);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dadosAtualizadosMedicos){
        service.atualizarDados(dadosAtualizadosMedicos);
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort ={"nome"})Pageable paginacao) {
        return service.listar(paginacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id){
       service.excluir(id);
    }

}
