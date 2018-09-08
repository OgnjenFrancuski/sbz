package rs.ac.uns.ftn.sbz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.sbz.backend.model.Medication;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Long>
{
    Optional<Medication> findById(Long id);

    Optional<Medication> findByName(String name);
}
