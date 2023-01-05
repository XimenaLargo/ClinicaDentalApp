package com.dh.ClinicaDental.service;
import com.dh.ClinicaDental.DTO.TurnoDTO;
import com.dh.ClinicaDental.exceptions.ResourceNotFoundException;
import com.dh.ClinicaDental.model.Odontologo;
import com.dh.ClinicaDental.model.Paciente;
import com.dh.ClinicaDental.model.Turno;
import com.dh.ClinicaDental.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Log4j
@Service
public class TurnoService {
    private  TurnoRepository turnoRepository;

    private PacienteService pacienteService;

    private OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    public TurnoDTO registrarTurno(TurnoDTO turno) throws ResourceNotFoundException {
        log.debug("inicio del metodo post: registrar turno");

        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(turno.getOdontologo_id());
        Optional<Paciente> pacienteBuscado =pacienteService.buscarPaciente(turno.getPaciente_id());

        if (odontologoBuscado.isPresent() && pacienteBuscado.isPresent()){
            Turno turnoEntity = new Turno();
            turnoEntity.setFecha(turno.getFecha());

            Paciente paciente = new Paciente();
            paciente.setId(turno.getPaciente_id());

            Odontologo odontologo = new Odontologo();
            odontologo.setId(turno.getOdontologo_id());

            turnoEntity.setPaciente(paciente);
            turnoEntity.setOdontologo(odontologo);

            Turno turnoGuardado = turnoRepository.save(turnoEntity);

            turno.setId(turnoGuardado.getId());

            log.info("Se creo correctamente el turno");
        }
        else {
            log.error("No se creo el turno");
            throw new ResourceNotFoundException("No se puede registrar");
        }
        return turno;
    }
    public Optional<TurnoDTO> buscarTurno(Long id) {
        log.debug("inicio del metodo get: buscar turno por id");
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);

        Optional<TurnoDTO> turnoDTOBuscado = Optional.empty();

        if (turnoBuscado.isPresent()){
            Turno turnoResultado = turnoBuscado.get();
            TurnoDTO turnoDTOADevolver = new TurnoDTO();
            turnoDTOADevolver.setId(turnoResultado.getId());
            turnoDTOADevolver.setFecha(turnoResultado.getFecha());
            turnoDTOADevolver.setOdontologo_id(turnoResultado.getOdontologo().getId());
            turnoDTOADevolver.setPaciente_id(turnoResultado.getPaciente().getId());

            turnoDTOBuscado = Optional.of(turnoDTOADevolver);

            log.info("El turno existe");
        }
        else {
           log.error("El turno no existe");
        }
        return turnoDTOBuscado;
    }
    public List<Turno> buscarTodos() throws ResourceNotFoundException {
        log.debug("inicio del metodo get: buscar todos los turnos registrados");
        List<Turno> turnosExistentes = turnoRepository.findAll();
        if (turnosExistentes.size()>0){
            log.info("Turnos registrados: " + turnosExistentes.size());
            return turnosExistentes;
        }
        else {
            log.error("No hay turnos registrados");
            throw new ResourceNotFoundException("No hay turnos registrados");
        }
    }

    public TurnoDTO actualizarTurno(TurnoDTO turno) throws ResourceNotFoundException {
        log.debug("inicio del metodo put: actualizar turno");
        Optional<TurnoDTO> turnoBuscado= buscarTurno(turno.getId());
        TurnoDTO turnoDTOADevolver = new TurnoDTO();
        if (turnoBuscado.isPresent()){
            Turno turnoEntity = new Turno();
            turnoEntity.setId(turno.getId());
            turnoEntity.setFecha(turno.getFecha());

            Paciente paciente = new Paciente();
            paciente.setId(turno.getPaciente_id());

            Odontologo odontologo = new Odontologo();
            odontologo.setId(turno.getOdontologo_id());

            turnoEntity.setPaciente(paciente);
            turnoEntity.setOdontologo(odontologo);

            Turno turnoGuardado = turnoRepository.save(turnoEntity);

            turnoDTOADevolver.setId(turnoGuardado.getId());
            turnoDTOADevolver.setOdontologo_id(turnoGuardado.getOdontologo().getId());
            turnoDTOADevolver.setPaciente_id(turnoGuardado.getPaciente().getId());
            turnoDTOADevolver.setFecha(turnoGuardado.getFecha());

            log.info("turno actualizado con exito");
        }
        else {
            log.error("El turno a actualizar no existe");
            throw new ResourceNotFoundException("El turno no existe");
        }
        return turnoDTOADevolver;
    }

    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        log.debug("inicio del metodo delete: eliminar turno");
        Optional<TurnoDTO> turnoBuscado = buscarTurno(id);
        if (turnoBuscado.isPresent()){
            turnoRepository.deleteById(id);
            log.info("turno eliminado con exito");
        }
        else {
            log.error("El turno a eliminar no existe");
          throw new ResourceNotFoundException("El turno no existe");
        }
    }

}
