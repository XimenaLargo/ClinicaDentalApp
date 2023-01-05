package com.dh.ClinicaDental.service;

import com.dh.ClinicaDental.DTO.TurnoDTO;
import com.dh.ClinicaDental.exceptions.BadRequestException;
import com.dh.ClinicaDental.exceptions.ResourceNotFoundException;
import com.dh.ClinicaDental.model.Domicilio;
import com.dh.ClinicaDental.model.Odontologo;
import com.dh.ClinicaDental.model.Paciente;
import com.dh.ClinicaDental.model.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void registrarPaciente() throws BadRequestException {
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

        Paciente  pacienteRegistrado = pacienteService.registrarPaciente(paciente);

        assertEquals(1, pacienteRegistrado.getId());
    }

    @Test
    @Order(2)
    void buscarPaciente() throws ResourceNotFoundException {

        Optional<Paciente>  pacienteABuscar = pacienteService.buscarPaciente(1L);
        assertNotNull(pacienteABuscar);

    }

    @Test
    @Order(3)
    void buscarTodos() throws BadRequestException {

        List<Paciente> pacientesRegistrados = pacienteService.buscarTodos();
        assertTrue(pacientesRegistrados.size() > 0);

    }

    @Test
    @Order(4)
    void actulizarPaciente() throws ResourceNotFoundException {

        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Roja");
        domicilio.setNumero(123);
        domicilio.setLocalidad("Av Las palmas");
        domicilio.setProvincia("Medellin");

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Juan");
        paciente.setApellido("Perez");
        paciente.setDni(12345);
        paciente.setDomicilio(domicilio);
        paciente.setFechaDeAlta(LocalDate.of(2022,5,15));

        Paciente pacienteActualizado = pacienteService.actulizarPaciente(paciente);

        assertEquals("Juan", pacienteActualizado.getNombre());

    }

    @Test
    @Order(5)
    void eliminarPaciente() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(1L);

        Optional<Paciente> pacienteEliminado = pacienteService.buscarPaciente(1L);

        assertEquals(pacienteEliminado, Optional.empty());
    }
}