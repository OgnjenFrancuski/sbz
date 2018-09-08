package rs.ac.uns.ftn.sbz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.sbz.backend.model.Symptom;

import java.util.Optional;

public interface SymptomRepository extends JpaRepository<Symptom, Long>
{
    Optional<Symptom> findById(Long id);

    Optional<Symptom> findByName(String name);
}
