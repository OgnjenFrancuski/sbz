package rs.ac.uns.ftn.sbz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.sbz.backend.model.Physician;

import java.util.Optional;

public interface PhysicianRepository extends JpaRepository<Physician, Long>
{
    Optional<Physician> findById(Long id);
}
