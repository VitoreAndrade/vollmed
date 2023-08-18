package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.DadosCadastrosEspecialidadesDto;
import med.voll.api.dto.dadosCadastroMedicosDto;

import java.util.ArrayList;
import java.util.List;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Long id_especialidade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
//    @Enumerated(value = EnumType.STRING)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Especialidade especialidade;


    @ManyToMany(mappedBy = "medicos")
    List<Consultorio> consultorios;

    @ManyToMany(mappedBy = "medicos")
    List<Especialidade> especialidades;



    private boolean ativo;

    public Medico(dadosCadastroMedicosDto dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.id_especialidade = dados.id_especialidade();
        this.crm = dados.crm();
        this.endereco = new Endereco(dados.endereco());
    }

    public void excluir() {
        this.ativo = false;
    }

    public Medico(Long id) {
        this.id = id;
    }


}
