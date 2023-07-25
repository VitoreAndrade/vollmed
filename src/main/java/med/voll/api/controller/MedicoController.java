package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.dadosCadastroMedicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.DadosAtualizacaoMedicos;
import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid dadosCadastroMedicos dados){
        repository.save(new Medico(dados));
    }
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort ={"nome"})Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }
    @PutMapping
    @Transactional

    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dadosAtualizadosMedicos){
        var medico = repository.getReferenceById(dadosAtualizadosMedicos.id());
        medico.atualizarInformacoes(dadosAtualizadosMedicos);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

    }

}
