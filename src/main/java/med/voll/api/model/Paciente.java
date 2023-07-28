package med.voll.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import med.voll.api.dto.dadosCadastrosPacientes;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pacientes")
//@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;
    private boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
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
