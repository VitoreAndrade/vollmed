package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.DadosCadastroConsultorioDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "consultorio")
@Table(name = "consultorio")
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = "id_consultorio")
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_consultorio;
    private String nome_consultorio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco_consultorio")
    private Endereco endereco;

//    @Enumerated(value = EnumType.STRING)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private Especialidade especialidade;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "medico_atende",
            joinColumns = @JoinColumn(name = "consultorio_id", updatable = false),
            inverseJoinColumns = @JoinColumn(name = "medico_id", updatable = false))
    List<Medico> medicos;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "consultorio_especialidade", joinColumns = @JoinColumn(name = "id_especialidades"),
            inverseJoinColumns = @JoinColumn(name = "id_consultorios"))
    List<Especialidade>especialidades;

    private boolean ativo;
    public Consultorio(DadosCadastroConsultorioDto dados) {
        this.ativo = true;
        this.nome_consultorio = dados.nome_consultorio();
//        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

        this.medicos = new ArrayList<>();
        for (Long i = 0L; i > dados.medicos().size() ; i++) {
                this.medicos.add(new Medico(i));
        }

//        this.especialidades = new ArrayList<>();
//        for (Long i = 0L; i >especialidades.size() ; i++) {
//            this.especialidades.add(new Especialidade());
//        }

    }
    public void excluir(){
        this.ativo = false;
    }
    public Consultorio (Long id){
        this.ativo = false;
    }

    public void excluirMedico(Long id){
        medicos.remove(id);
    }
}