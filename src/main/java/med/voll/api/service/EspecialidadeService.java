package med.voll.api.service;

import med.voll.api.dto.DadosCadastrosEspecialidadesDto;
import med.voll.api.model.Especialidade;
import med.voll.api.repositorio.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadeService {
    @Autowired
    private EspecialidadeRepository repository;
    public void cadastrarEspecialidade(DadosCadastrosEspecialidadesDto dados){
        repository.save(new Especialidade(dados));
    }

}
