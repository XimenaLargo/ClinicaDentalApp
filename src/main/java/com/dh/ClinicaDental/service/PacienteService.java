package com.dh.ClinicaDental.service;
import com.dh.ClinicaDental.exceptions.BadRequestException;
import com.dh.ClinicaDental.exceptions.ResourceNotFoundException;
import com.dh.ClinicaDental.model.Paciente;
import com.dh.ClinicaDental.repository.PacienteRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Log4j
@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    public Paciente registrarPaciente(Paciente paciente) throws BadRequestException {
        log.debug("Inicio del metodo post: registrar paciente");
        if (paciente != null){
            log.info("Se registro correctamente el paciente");
            return pacienteRepository.save(paciente);
        }
        else {
            log.error("Fallo el registro del paciente");
            throw new BadRequestException("No se puede registrar");
        }
    }
    public Optional<Paciente> buscarPaciente(Long id){
        log.debug("inicio del metodo get: buscar paciente por id");
        return pacienteRepository.findById(id);
    }
    public List<Paciente> buscarTodos() throws BadRequestException {
        log.debug("inicio del metodo get: buscar todos los pacientes registrados");
        List<Paciente> pacientesEncontrados =pacienteRepository.findAll();
        if (pacientesEncontrados.size()>0){
            log.info("Pacientes registrados: " + pacientesEncontrados.size());
            return pacientesEncontrados;
        }
        else {
            log.error("No hay pacientes registrados");
            throw new BadRequestException("No hay pacientes registrados");
        }
    }
    public Paciente actulizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        log.debug("inicio del metodo put: actualizar paciente");
        Optional<Paciente> pacienteBuscado= buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent()){
            log.info("Paciente actualizado con exito");
            return pacienteRepository.save(paciente);
        }
        else {
            log.error("El paciente a actualizar no existe");
            throw new ResourceNotFoundException("El paciente no existe");
        }
    }
    public void eliminarPaciente (Long id) throws ResourceNotFoundException {
        log.debug("inicio del metodo delete: eliminar paciente");
        if (buscarPaciente(id).isPresent()){
            log.info("paciente eliminado con exito");
            pacienteRepository.deleteById(id);
        }
        else {
            log.error("El paciente a eliminar no existe");
            throw new ResourceNotFoundException("El paciente no existe");
        }
    }
}
