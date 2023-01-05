package com.dh.ClinicaDental.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Data
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private Integer dni;
    @Column
    private LocalDate fechaDeAlta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> turnos;

    public Paciente(){
    }

}
