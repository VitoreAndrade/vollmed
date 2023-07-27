package med.voll.api.service;

import med.voll.api.dto.dadosAtualizacaoPacientes;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import med.voll.api.repositorio.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Pacienteservice {
    @Autowired
    private PacienteRepository repository;

    public void atualizarInformacoesPaciente(dadosAtualizacaoPacientes dadosAtualizadosPacientes) {
        Paciente paciente = repository.findById(dadosAtualizadosPacientes.id()).get();


        if(dadosAtualizadosPacientes.nome() != null){
            paciente.setNome(dadosAtualizadosPacientes.nome());
        };
        if(dadosAtualizadosPacientes.telefone() != null) {
            paciente.setTelefone(dadosAtualizadosPacientes.telefone());
        }
        if (dadosAtualizadosPacientes.endereco() != null){
            paciente.getEndereco().atualizarInformacoes(dadosAtualizadosPacientes.endereco());
        }


    }
}
