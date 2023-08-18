package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.DadosCadastroAgendamentoDto;

import java.util.Date;
@Entity
@Table(name = "agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Medico medico;
    private Paciente paciente;
    private Consultorio consultorio;
    private String data;
    private String hora;


public Agendamento (DadosCadastroAgendamentoDto dados){
    this.medico = dados.medico();
    this.paciente = dados.paciente();
    this.consultorio = dados.consultorio();
    this.data = dados.data();
    this.hora = dados.hora();
}
}
