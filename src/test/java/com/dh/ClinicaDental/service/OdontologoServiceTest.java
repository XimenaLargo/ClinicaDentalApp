package com.dh.ClinicaDental.service;

import com.dh.ClinicaDental.DTO.TurnoDTO;
import com.dh.ClinicaDental.exceptions.BadRequestException;
import com.dh.ClinicaDental.exceptions.ResourceNotFoundException;
import com.dh.ClinicaDental.model.Odontologo;
import com.dh.ClinicaDental.model.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void registrarOdontologo() throws BadRequestException {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Pedro");
        odontologo.setApellido("Perez");
        odontologo.setMatricula(45678);

        odontologoService.registrarOdontologo(odontologo);
    }

    @Test
    @Order(2)
    void buscarOdontologo() throws ResourceNotFoundException {

        Optional<Odontologo> odontologoABuscar = odontologoService.buscarOdontologo(1L);
        assertNotNull(odontologoABuscar);

    }

    @Test
    @Order(3)
    void buscarTodos() throws BadRequestException {

        List<Odontologo> odontologosRegistrados = odontologoService.buscarTodos();
        assertTrue(odontologosRegistrados.size() > 0);

    }

    @Test
    @Order(4)
    void actulizarOdontologo() throws ResourceNotFoundException {

        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        odontologo.setNombre("Ana");
        odontologo.setApellido("Cano");
        odontologo.setMatricula(45678);

    Odontologo odontologoActualizado = odontologoService.actulizarOdontologo(odontologo);

        assertEquals("Ana", odontologoActualizado.getNombre());
    }

    @Test
    @Order(5)
    void eliminarOdontologo() throws ResourceNotFoundException {

        odontologoService.eliminarOdontologo(1L);

        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologo(1L);

        assertEquals(odontologoEliminado, Optional.empty());
    }
}