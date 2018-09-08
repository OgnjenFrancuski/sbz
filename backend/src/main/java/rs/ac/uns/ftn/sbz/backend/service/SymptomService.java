package rs.ac.uns.ftn.sbz.backend.service;

import rs.ac.uns.ftn.sbz.backend.model.Symptom;

import java.util.Set;


public interface SymptomService
{
    Symptom create(Symptom symptom);

    Symptom update(Symptom symptom);

    void delete(Long id);

    Symptom getOne(Long id);

    Set<Symptom> getAll();

    Symptom findByName(String name);
}
