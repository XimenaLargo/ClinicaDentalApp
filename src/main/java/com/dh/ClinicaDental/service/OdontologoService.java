package com.dh.ClinicaDental.service;
import com.dh.ClinicaDental.exceptions.BadRequestException;
import com.dh.ClinicaDental.exceptions.ResourceNotFoundException;
import com.dh.ClinicaDental.model.Odontologo;
import com.dh.ClinicaDental.repository.OdontologoRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Log4j
@Service
public class OdontologoService {


    private final OdontologoRepository odontologoRepository;

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    public Odontologo registrarOdontologo(Odontologo odontologo) throws BadRequestException {
        log.debug("Inicio del metodo post: registrar odontologo");
        if (odontologo != null){
            log.info("Se registro correctamente el odontologo");
            return odontologoRepository.save(odontologo);
        }
        else {
            log.error("Fallo el registro");
           throw new BadRequestException("No se puede registrar");
        }
    }
    public Optional<Odontologo> buscarOdontologo(Long id) {
        log.debug("inicio del metodo get: buscar odontologo por id");
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarTodos() throws BadRequestException {
        log.debug("inicio del metodo get: buscar todos los odontologos registrados");
        List<Odontologo> odontologosEncontrados =odontologoRepository.findAll();
        if (odontologosEncontrados.size()>0){
            log.info("Odontologos registrados: " + odontologosEncontrados.size());
            return odontologosEncontrados;
        }
        else {
            log.error("No hay odontologos registrados");
            throw new BadRequestException("No hay odontologos registrados");
        }
    }
    public Odontologo actulizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException {
        log.debug("inicio del metodo put: actualizar odontologo");
        Optional<Odontologo> odontologoBuscado= buscarOdontologo(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            log.info("odontologo actualizado con exito");
         return odontologoRepository.save(odontologo);
        }
        else {
            log.error("El odontologo a actualizar no existe");
            throw new ResourceNotFoundException("El odontologo no existe");
        }
    }
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        log.debug("inicio del metodo delete: eliminar odontologo");
        if (buscarOdontologo(id).isPresent()){
            log.info("odontologo eliminado con exito");
            odontologoRepository.deleteById(id);
        }
        else {
            log.error("El odontologo a eliminar no existe");
            throw new ResourceNotFoundException("El odontologo no existe");
        }
    }
}
