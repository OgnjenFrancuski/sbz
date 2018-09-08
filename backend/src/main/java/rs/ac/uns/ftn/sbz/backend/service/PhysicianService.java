package rs.ac.uns.ftn.sbz.backend.service;

import rs.ac.uns.ftn.sbz.backend.model.Physician;

import java.util.Set;


public interface PhysicianService
{
    Physician create(Physician physician);

    Physician update(Physician physician);

    void delete(Long id);

    Physician getOne(Long id);

    Physician getByUsername();

    Set<Physician> getAll();
}
