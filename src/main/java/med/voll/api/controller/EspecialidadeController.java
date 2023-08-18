package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastrosEspecialidadesDto;
import med.voll.api.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {
    @Autowired
    private EspecialidadeService service;

    @PostMapping
    @Transactional
    public void cadastrarEspecialidade(@RequestBody @Valid DadosCadastrosEspecialidadesDto dados){
        service.cadastrarEspecialidade(dados);
    }
}
