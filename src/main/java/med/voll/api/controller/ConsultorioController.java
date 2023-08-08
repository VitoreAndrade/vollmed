package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroConsultorioDto;
import med.voll.api.model.Consultorio;
import med.voll.api.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultorio")
public class ConsultorioController {
    @Autowired
    private ConsultorioService service;
    @PostMapping
    @Transactional
    public void cadastrar (@RequestBody @Valid DadosCadastroConsultorioDto dados){
        service.cadastarConsultorio(dados);
    }

}
