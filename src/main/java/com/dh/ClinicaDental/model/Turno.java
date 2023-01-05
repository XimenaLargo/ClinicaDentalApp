package com.dh.ClinicaDental.model;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "turnos")
public class Turno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @ManyToOne
  @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;

    @Column
    private LocalDate fecha;

    public Turno() {
    }
}
