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
@Entity
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

    @Enumerated(value = EnumType.STRING)
    private Especialidade especialidade;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "medico_atende",
            joinColumns = @JoinColumn(name = "consultorio_id"),
            inverseJoinColumns = @JoinColumn(name = "medico_id"))
    List<Medico> medicos;

    private boolean ativo;
    public Consultorio(DadosCadastroConsultorioDto dados) {
        this.ativo = true;
        this.nome_consultorio = dados.nome_consultorio();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

        this.medicos = new ArrayList<>();
        for (Long i = 0L; i > dados.medicos().size() ; i++) {
            this.medicos.add(new Medico(i));
        }

    }
    public void excluir(){
        this.ativo = false;
    }
    public Consultorio (Long id)
}