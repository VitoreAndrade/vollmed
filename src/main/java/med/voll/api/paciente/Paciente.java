package med.voll.api.paciente;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosAtualizacaoMedicos;
@Getter
@Setter
public class Paciente {
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

    public void atualizarInformacoesPaciente(dadosAtualizacaoPacientes dadosAtualizadosPacientes) {
        if(dadosAtualizadosPacientes.nome() != null){
            this.nome = dadosAtualizadosPacientes.nome();
        };
        if(dadosAtualizadosPacientes.telefone() != null) {
            this.telefone = dadosAtualizadosPacientes.telefone();
        }
        if (dadosAtualizadosPacientes.endereco() != null){
            this.endereco.atualizarInformacoes(dadosAtualizadosPacientes.endereco());
        }


    }


    public void inativar() {
        this.ativo = false;
    }
}
