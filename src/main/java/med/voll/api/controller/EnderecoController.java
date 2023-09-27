package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosEndereco;
import med.voll.api.service.EnderecoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class EnderecoController {

    private EnderecoService service;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosEndereco dados){
        service.cadastrar(dados);
    }
}
