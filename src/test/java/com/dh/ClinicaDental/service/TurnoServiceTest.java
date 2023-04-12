package com.dh.ClinicaDental.service;

import com.dh.ClinicaDental.DTO.TurnoDTO;
import com.dh.ClinicaDental.exceptions.BadRequestException;
import com.dh.ClinicaDental.exceptions.ResourceNotFoundException;
import com.dh.ClinicaDental.model.Domicilio;
import com.dh.ClinicaDental.model.Odontologo;
import com.dh.ClinicaDental.model.Paciente;
import com.dh.ClinicaDental.model.Turno;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Order;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    void registrarTurno() throws ResourceNotFoundException, BadRequestException {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("falsa");
        domicilio.setNumero(123);
        domicilio.setLocalidad("Av siempre viva");
        domicilio.setProvincia("Springfield");

        Paciente paciente = new Paciente();
        paciente.setNombre("Maria");
        paciente.setApellido("Perez");
        paciente.setDni(12345);
        paciente.setDomicilio(domicilio);
        paciente.setFechaDeAlta(LocalDate.of(2022,5,15));

        pacienteService.registrarPaciente(paciente);

        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Pedro");
        odontologo.setApellido("Perez");
        odontologo.setMatricula(45678);

        odontologoService.registrarOdontologo(odontologo);

        TurnoDTO turno = new TurnoDTO();
        turno.setFecha(LocalDate.of(2022,12,22));
        turno.setOdontologo_id(odontologo.getId());
        turno.setPaciente_id(paciente.getId());

        TurnoDTO turnoGuardado = turnoService.registrarTurno(turno);

        assertEquals(1, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarTurno(){

        Optional<TurnoDTO> turnoABuscar = turnoService.buscarTurno(1L);
        assertNotNull(turnoABuscar);
    }

    @Test
    @Order(3)
    void buscarTodos() throws ResourceNotFoundException {
        List<Turno> turnosRegistrados = turnoService.buscarTodos();
        assertTrue(turnosRegistrados.size() > 0);
    }

    @Test
    @Order(4)
    void actualizarTurno() throws ResourceNotFoundException, BadRequestException {

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("falsa");
        domicilio2.setNumero(123);
        domicilio2.setLocalidad("Av siempre viva");
        domicilio2.setProvincia("Springfield");

        Paciente paciente2 = new Paciente();
        paciente2.setNombre("Rosa");
        paciente2.setApellido("Velez");
        paciente2.setDni(98534);
        paciente2.setDomicilio(domicilio2);
        paciente2.setFechaDeAlta(LocalDate.of(2022,11,15));

        pacienteService.registrarPaciente(paciente2);

        Odontologo odontologo2 = new Odontologo();
        odontologo2.setNombre("Mario");
        odontologo2.setApellido("Lopez");
        odontologo2.setMatricula(78346);

        odontologoService.registrarOdontologo(odontologo2);

        TurnoDTO turno = new TurnoDTO();
        turno.setId(1L);
        turno.setFecha(LocalDate.of(2022,12,22));
        turno.setOdontologo_id(odontologo2.getId());
        turno.setPaciente_id(paciente2.getId());

        TurnoDTO turnoGuardado = turnoService.actualizarTurno(turno);

        assertEquals(odontologo2.getId(), turnoGuardado.getOdontologo_id());

    }

    @Test
    @Order(5)
    void eliminarTurno() throws ResourceNotFoundException {

            turnoService.eliminarTurno(1L);

            Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(1L);

            assertEquals(turnoEliminado, Optional.empty());

    }
}