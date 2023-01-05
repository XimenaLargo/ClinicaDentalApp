package com.dh.ClinicaDental.repository;
import com.dh.ClinicaDental.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
