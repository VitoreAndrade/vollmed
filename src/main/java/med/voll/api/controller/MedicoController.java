package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosEndereco;
import med.voll.api.dto.dadosCadastroMedicosDto;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.dto.DadosAtualizacaoMedicosDto;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoService service;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid dadosCadastroMedicosDto dados, UriComponentsBuilder uriBuilder){
        return service.cadastrar(dados,uriBuilder);
    }


    @PutMapping
    @Transactional
    public ResponseEntity  atualizar(@RequestBody @Valid DadosAtualizacaoMedicosDto dadosAtualizadosMedicos){
         return service.atualizarDados(dadosAtualizadosMedicos);

    }

    @GetMapping
    public ResponseEntity<Page <DadosListagemMedico>>listar(@PageableDefault(size = 10, sort ={"nome"})Pageable paginacao) {
        return service.listar(paginacao);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id){
      return service.excluir(id);

    }
    @GetMapping("/{id}")
    public ResponseEntity detalhamento(@PathVariable Long id){
        return service.detalhamento(id);

    }

}
