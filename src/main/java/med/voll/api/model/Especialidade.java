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
//
//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(name = "consultorio_especialidade", joinColumns = @JoinColumn(name = "id_especialidades"),
//            inverseJoinColumns = @JoinColumn(name = "id_consultorios"))
//            List<Consultorio> consultorios;
////    List<Especialidade>especialidades;
////
    @ManyToMany(mappedBy = "especialidades")
    List<Consultorio> consultorios;
    public Especialidade (DadosCadastrosEspecialidadesDto dados){
     this.nomeEspecialidade = dados.nomeEspecialidade();
     this.consultorios = new ArrayList<>();
        for (Long i = 0L; i >dados.consultorios().size() ; i++) {
            this.consultorios.add(new Consultorio(i));
        }
    }



}
