package med.voll.api.service;

import med.voll.api.dto.DadosListagemPaciente;
import med.voll.api.dto.dadosAtualizacaoPacientesDto;
import med.voll.api.dto.DadosCadastrosPacientes;
import med.voll.api.model.Paciente;
import med.voll.api.repositorio.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public void cadastrar( DadosCadastrosPacientes dados){
        repository.save(new Paciente(dados));
    }

    public Paciente  atualizarInformacoesPaciente(dadosAtualizacaoPacientesDto dadosAtualizadosPacientes) {
        Paciente paciente = repository.findById(dadosAtualizadosPacientes.id()).get();


        if(dadosAtualizadosPacientes.nome() != null){
            paciente.setNome(dadosAtualizadosPacientes.nome());
        };
        if(dadosAtualizadosPacientes.telefone() != null) {
            paciente.setTelefone(dadosAtualizadosPacientes.telefone());
        }
//        if (dadosAtualizadosPacientes.endereco() != null){
//            paciente.getEndereco().atualizarInformacoes(dadosAtualizadosPacientes.endereco());
//        }
        return repository.saveAndFlush(paciente);
    }

    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    public void remover(Long id){
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
    }
}
