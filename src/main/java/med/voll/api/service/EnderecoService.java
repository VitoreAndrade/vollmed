package med.voll.api.service;

import med.voll.api.dto.DadosEndereco;
import med.voll.api.model.Endereco;
import med.voll.api.repositorio.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
  @Autowired
    private EnderecoRepository repository;

    public void cadastrar( DadosEndereco dados){
        repository.save(new Endereco(dados));

    }

}
