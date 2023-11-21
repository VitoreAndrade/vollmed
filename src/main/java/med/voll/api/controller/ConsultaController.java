package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dt.DadosAgendamentoConsulta;
import med.voll.api.dto.DadosCancelamentoConsulta;
import med.voll.api.dto.DadosDetalhamentoConsulta;
import med.voll.api.dto.DadosDetalhamentoPaciente;
import med.voll.api.infra.TratadorDeErros;
import med.voll.api.infra.ValidacaoException;
import med.voll.api.service.AgendaDeConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultasService agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar (@RequestBody @Valid DadosAgendamentoConsulta dados){
       var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
    agenda.cancelar(dados);
    return  ResponseEntity.noContent().build();
            }

}
