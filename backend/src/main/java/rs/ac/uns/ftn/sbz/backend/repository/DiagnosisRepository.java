package rs.ac.uns.ftn.sbz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.sbz.backend.model.Diagnosis;

import java.util.Optional;
import java.util.Set;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long>
{
    Optional<Diagnosis> findById(Long id);

    @Query("SELECT d FROM Diagnosis d WHERE d.patient.id = :patient_id")
    Set<Diagnosis> findAllByPatientId(@Param("patient_id") Long patientId);

    @Query("SELECT d FROM Diagnosis d WHERE d.patient.id = :patient_id")
    Set<Diagnosis> findAllByPhysicianId(Long physicianId);
}
