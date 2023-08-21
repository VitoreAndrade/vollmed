package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.DadosCadastroAgendamentoDto;

import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Medico medico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Paciente paciente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_consultorio")
    private Consultorio consultorio;

    private String data_agendamento;
    private String hora;

 @ManyToMany(mappedBy = "agendamentos")
 List<Consultorio>consultorios;

public Agendamento (DadosCadastroAgendamentoDto dados){

    this.data_agendamento = dados.data_agendamento();
    this.hora = dados.hora();

}

    public Agendamento(Consultorio consulta, Medico medicos, Paciente pacientes) {
        this.consultorio = consulta;
        this.medico = medicos;
        this.paciente = pacientes;
    }
}
