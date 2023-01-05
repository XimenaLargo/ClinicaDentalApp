package com.dh.ClinicaDental.controller;
import com.dh.ClinicaDental.exceptions.BadRequestException;
import com.dh.ClinicaDental.exceptions.ResourceNotFoundException;
import com.dh.ClinicaDental.model.Odontologo;
import com.dh.ClinicaDental.service.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registroOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).body(odontologoService.registrarOdontologo(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity< Optional<Odontologo>> buscarOdontologo(@PathVariable Long id)  {
        return ResponseEntity.status(HttpStatus.OK).body(odontologoService.buscarOdontologo(id));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>>  listarOdontologos() throws BadRequestException {
       return ResponseEntity.status(HttpStatus.OK).body(odontologoService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
       return ResponseEntity.status(HttpStatus.OK).body(odontologoService.actulizarOdontologo(odontologo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.status(HttpStatus.OK).body("El odontologo con id " + id + " ha sido eliminado.");
    }

}