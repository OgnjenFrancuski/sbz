package rs.ac.uns.ftn.sbz.backend.service;

import rs.ac.uns.ftn.sbz.backend.model.Diagnosis;
import rs.ac.uns.ftn.sbz.backend.model.Medication;

import java.util.List;
import java.util.Set;


public interface DiagnosisService
{
    Diagnosis create(Diagnosis diagnosis);

    Diagnosis update(Diagnosis diagnosis);

    void delete(Long id);

    Diagnosis getOne(Long id);

    Set<Diagnosis> getAll();

    Set<Diagnosis> getAllByPatientId(Long patientId);

    Set<Diagnosis> getAllByPatientIdAndDiseaseId(Long patientId, Long diseaseId);

    Set<Diagnosis> getAllByPhysicianId(Long physicianId);
}
