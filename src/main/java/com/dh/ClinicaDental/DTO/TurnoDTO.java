package com.dh.ClinicaDental.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TurnoDTO {

    private Long id;
    private Long odontologo_id;
    private Long paciente_id;
    private LocalDate fecha;


}
