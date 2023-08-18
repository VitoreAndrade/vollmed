package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoService service;

    @PostMapping
    @Transactional
    public void agendarConsulta (@RequestBody @Valid Long consultorio, Long medico, Long paciente){
        service.cadastrarAgendamento(consultorio,medico,paciente);
    }
}
