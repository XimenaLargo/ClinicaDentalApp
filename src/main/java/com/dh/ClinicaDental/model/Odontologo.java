package com.dh.ClinicaDental.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String apellido;

    @Column
    private String nombre;

    @Column
    private Integer matricula;

    @OneToMany(mappedBy = "odontologo")
    @JsonIgnore
    private Set<Turno> turnos;

    public Odontologo(){
    }

}
