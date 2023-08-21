package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoService service;

    @PostMapping("/consultorio/{id_consultorio}/medicoAgendado/{id}/pacienteAgendado/{id_paciente}")
    @Transactional
    public void agendarConsulta (@RequestBody @Valid @PathVariable("id_consultorio") Long consultorio, @PathVariable("id") Long medico, @PathVariable("id_paciente") Long paciente){
        System.out.println(paciente);
        service.cadastrarAgendamento(consultorio,medico,paciente);
    }
}
