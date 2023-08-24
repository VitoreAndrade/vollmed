package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroAgendamentoDto;
import med.voll.api.service.AgendamentoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoService service;
    @PostMapping
    @Transactional
    public String agendar(@RequestBody @Valid DadosCadastroAgendamentoDto agenda) throws DadosErro {
        return service.cadastrarAgendamneto(agenda);

    }

}

