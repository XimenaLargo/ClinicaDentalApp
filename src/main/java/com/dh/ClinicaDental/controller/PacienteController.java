package com.dh.ClinicaDental.controller;
import com.dh.ClinicaDental.exceptions.BadRequestException;
import com.dh.ClinicaDental.exceptions.ResourceNotFoundException;
import com.dh.ClinicaDental.model.Odontologo;
import com.dh.ClinicaDental.model.Paciente;
import com.dh.ClinicaDental.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registroPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.registrarPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPaciente(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.buscarPaciente(id));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>>  listarPacientes() throws BadRequestException {
       return ResponseEntity.status(HttpStatus.OK).body(pacienteService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.actulizarPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.status(HttpStatus.OK).body("El paciente con id " + id + " ha sido eliminado.");
    }

}
