package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.DadosCadastrosEspecialidadesDto;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "especialidade")
@Table(name = "especialidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Especialidade {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEspecialidade;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "medicos",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidade"))
    List<Medico> medicos;


    public Especialidade (DadosCadastrosEspecialidadesDto dados){
     this.nomeEspecialidade = dados.nomeEspecialidade();

    }
//    public Especialidade(String especialidade){
//        this.nomeEspecialidade = especialidade;
//    }


}
