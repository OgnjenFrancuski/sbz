package rs.ac.uns.ftn.sbz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.sbz.backend.model.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>
{
    Optional<Patient> findById(Long id);
}
