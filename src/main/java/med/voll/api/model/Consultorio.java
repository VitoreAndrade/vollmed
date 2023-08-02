package med.voll.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import med.voll.api.dto.DadosCadastroConsultorioDto;

//@Getter
//@Setter
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(of = "id_consultorio")
//public class Consultorio {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id_consultorio;
//    private Medico medico;
//    private Endereco endereco;
//    private String nome;
//    private Especialidade especialidade;
//
//
//    public Consultorio(DadosCadastroConsultorioDto dados){
//        this.medico = dados.medico();
//        this.endereco = dados.endereco();
//        this.nome = dados.nome();
//        this.especialidade = dados.especialidade();
//    }
//
//}
