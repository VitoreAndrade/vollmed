package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.dadosCadastroMedicosDto;

@Table(name = "medicos")
@Entity(name = "medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private int endereco_id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;


    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(dadosCadastroMedicosDto dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void excluir() {
        this.ativo = false;
    }
}
