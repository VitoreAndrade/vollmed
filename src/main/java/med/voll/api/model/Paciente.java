package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.dadosCadastrosPacientes;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "paciente")
@Table(name = "pacientes")
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;
    private boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco_pacientes")
    private Endereco endereco;

    public Paciente (dadosCadastrosPacientes dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void inativar() {
        this.ativo = false;
    }
}
