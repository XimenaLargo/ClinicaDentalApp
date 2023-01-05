package com.dh.ClinicaDental.controller;
import com.dh.ClinicaDental.DTO.TurnoDTO;
import com.dh.ClinicaDental.exceptions.ResourceNotFoundException;
import com.dh.ClinicaDental.model.Odontologo;
import com.dh.ClinicaDental.model.Paciente;
import com.dh.ClinicaDental.model.Turno;
import com.dh.ClinicaDental.service.OdontologoService;
import com.dh.ClinicaDental.service.PacienteService;
import com.dh.ClinicaDental.service.TurnoService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Log4j
@RestController
@RequestMapping("/turnos")
public class TurnoController {


    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException {
       return ResponseEntity.status(HttpStatus.OK).body(turnoService.registrarTurno(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TurnoDTO>> buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
       return ResponseEntity.status(HttpStatus.OK).body(turnoService.buscarTurno(id));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() throws ResourceNotFoundException {
      return ResponseEntity.status(HttpStatus.OK).body(turnoService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(turnoService.actualizarTurno(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.status(HttpStatus.OK).body("El turno con id: " + id + " no existe");
    }
}
