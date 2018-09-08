package rs.ac.uns.ftn.sbz.backend.service;

import rs.ac.uns.ftn.sbz.backend.model.Disease;
import rs.ac.uns.ftn.sbz.backend.model.Symptom;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface DiseaseService
{
    Disease create(Disease disease);

    Disease update(Disease disease);

    void delete(Long id);

    Disease getOne(Long id);

    Set<Disease> getAll();
}
