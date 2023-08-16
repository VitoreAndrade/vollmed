package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.*;
import med.voll.api.model.Consultorio;
import med.voll.api.model.Medico;
import med.voll.api.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("consultorio")
public class ConsultorioController {
    @Autowired
    private ConsultorioService service;
    @PostMapping
    @Transactional
    public void cadastrar (@RequestBody @Valid DadosCadastroConsultorioDto dados){
        service.cadastarConsultorio(dados);
    }

    @PostMapping("/{consultorio}/novoMedico/{id}")
    @Transactional
    public void adicionarMedico (@RequestBody @Valid @PathVariable ("consultorio") Long consultorio, @PathVariable("id") Medico medico){
        service.addMedico(consultorio, medico);
    }

 @GetMapping
    public Page<DadosListagemConsultorio> listarConsultorio(@PageableDefault(size = 10) Pageable paginacao) {
        return service.listarConsultorio(paginacao);
    }

    @PutMapping
    @Transactional
    public void atualizarConsultorio(@RequestBody @Valid DadosAtualizacaoConsultorioDto dados){
        service.atualizarDadosConsultorio(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirConsulotrio (@PathVariable Long id){
      service.exluirConsultorio(id);
    }

    @DeleteMapping("/{consultorio}/medico/{id}")
    @Transactional
    public void excluirMedico (@PathVariable("consultorio") Long consultorio, @PathVariable("id") Long id){
        service.deleteMedico(consultorio, id);
    }




}