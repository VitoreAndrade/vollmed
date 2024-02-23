package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.DadosCadastroConsultorioDto;
import med.voll.api.dto.DadosCadastrosEspecialidadesDto;

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

public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_consultorio;

    private String nome_consultorio;
    private Long id_especialidade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco_consultorio")
    private Endereco endereco;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "medico_atende",
            joinColumns = @JoinColumn(name = "consultorio_id", updatable = false),
            inverseJoinColumns = @JoinColumn(name = "medico_id", updatable = false))
    List<Medico> medicos;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "consultorio_paciente",
            joinColumns = @JoinColumn(name = "id_consultorios", updatable = false),
            inverseJoinColumns = @JoinColumn(name = "id_paciente", updatable = false))
    List<Paciente> pacientes;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "consulta_agenda",
            joinColumns = @JoinColumn(name = "id_consultorio", updatable = false),
            inverseJoinColumns = @JoinColumn(name = "id_agendamento", updatable = false))
    List<Agendamento> agendamentos;

    private boolean ativo;
    public Consultorio(DadosCadastroConsultorioDto dados) {
        this.ativo = true;
        this.nome_consultorio = dados.nome_consultorio();
        this.endereco = new Endereco(dados.endereco());
        this.id_especialidade = dados.id_especialidades();
        this.medicos = new ArrayList<>();
        for (int i = 0; i < dados.medicos().size() ; i++) {
            this.medicos.add(new Medico(dados.medicos().get(i)));
        }
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