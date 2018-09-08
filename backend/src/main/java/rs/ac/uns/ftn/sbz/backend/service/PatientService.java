package rs.ac.uns.ftn.sbz.backend.service;

import rs.ac.uns.ftn.sbz.backend.model.Patient;

import java.util.Set;


public interface PatientService
{
    Patient create(Patient patient);

    Patient update(Patient patient);

    void delete(Long id);

    Patient getOne(Long id);

    Set<Patient> getAll();
}
