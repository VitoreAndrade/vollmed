package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosListagemPaciente;
import med.voll.api.dto.dadosAtualizacaoPacientes;
import med.voll.api.dto.dadosCadastrosPacientes;
import med.voll.api.model.Paciente;
import med.voll.api.repositorio.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class ControllerPaciente {
    @Autowired
    private PacienteRepository repository;


    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid dadosCadastrosPacientes dados){
        repository.save(new Paciente(dados));
    }


    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }
//    @PutMapping
//    @Transactional
//    public void atualizar(@RequestBody @Valid dadosAtualizacaoPacientes dadosAtualizadosPacientes){
//        var paciente = repository.getReferenceById(dadosAtualizadosPacientes.id());
//        paciente.atualizarInformacoesPaciente(dadosAtualizadosPacientes);
//    }


    public void remover(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
    }

}