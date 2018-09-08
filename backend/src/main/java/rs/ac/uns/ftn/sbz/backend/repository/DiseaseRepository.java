package rs.ac.uns.ftn.sbz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.sbz.backend.model.Disease;

import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<Disease, Long>
{
    Optional<Disease> findById(Long id);
}
