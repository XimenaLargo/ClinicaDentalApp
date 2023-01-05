package com.dh.ClinicaDental.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String calle;
    @Column
    private Integer numero;
    @Column
    private String localidad;
    @Column
    private String provincia;

    @OneToOne(mappedBy = "domicilio")
    @JsonIgnore
    private Paciente paciente;

    public Domicilio() {
    }
}