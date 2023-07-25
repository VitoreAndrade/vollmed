package med.voll.api.paciente;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class ControllerPaciente {
    @Autowired
    private PacienteRepository repository;
    @PutMapping
    @Transactional

    public void atualizar(@RequestBody @Valid dadosAtualizacaoPacientes dadosAtualizadosPacientes){
        var paciente = repository.getReferenceById(dadosAtualizadosPacientes.id());
        paciente.atualizarInformacoesPaciente(dadosAtualizadosPacientes);
    }

    public void remover(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
    }

}
