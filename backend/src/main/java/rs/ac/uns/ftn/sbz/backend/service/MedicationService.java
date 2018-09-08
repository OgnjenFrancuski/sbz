package rs.ac.uns.ftn.sbz.backend.service;

import rs.ac.uns.ftn.sbz.backend.model.Medication;

import java.util.Set;


public interface MedicationService
{
    Medication create(Medication medication);

    Medication update(Medication medication);

    void delete(Long id);

    Medication getOne(Long id);

    Set<Medication> getAll();
}
