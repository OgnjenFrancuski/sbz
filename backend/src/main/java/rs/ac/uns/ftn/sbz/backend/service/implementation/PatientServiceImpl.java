package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.exception.NotFoundException;
import rs.ac.uns.ftn.sbz.backend.model.Ingredient;
import rs.ac.uns.ftn.sbz.backend.model.Patient;
import rs.ac.uns.ftn.sbz.backend.repository.IngredientRepository;
import rs.ac.uns.ftn.sbz.backend.repository.PatientRepository;
import rs.ac.uns.ftn.sbz.backend.service.PatientService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PatientServiceImpl implements PatientService
{
    private final IngredientRepository ingredientRepository;
    private final PatientRepository patientRepository;


    @Autowired
    public PatientServiceImpl(IngredientRepository ingredientRepository, PatientRepository patientRepository)
    {
        this.ingredientRepository = ingredientRepository;
        this.patientRepository = patientRepository;
    }


    @Override
    public Patient create(Patient patient)
    {
        Set<Ingredient> ingredients = patient.getAllergicIngredients().stream().map(i -> this.ingredientRepository.findById(i.getId())
                .orElseThrow(NotFoundException::new)).collect(Collectors.toSet());

        patient.setAllergicIngredients(ingredients);
        patient.setId(null);

        return this.patientRepository.save(patient);
    }


    @Override
    public Patient update(Patient patient)
    {
        Patient patientDB = this.patientRepository.findById(patient.getId()).orElseThrow(NotFoundException::new);

        patientDB.setFirstName(patient.getFirstName());
        patientDB.setLastName(patient.getLastName());
        patientDB.setPersonalId(patient.getPersonalId());

        Set<Ingredient> ingredients = patient.getAllergicIngredients().stream().map(i -> this.ingredientRepository.findById(i.getId())
                .orElseThrow(NotFoundException::new)).collect(Collectors.toSet());

        patientDB.setAllergicIngredients(ingredients);

        return this.patientRepository.save(patientDB);
    }


    @Override
    public void delete(Long id)
    {
        this.patientRepository.deleteById(id);
    }


    @Override
    public Patient getOne(Long id)
    {
        return this.patientRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @Override
    public Set<Patient> getAll()
    {
        return new HashSet<>(this.patientRepository.findAll());
    }
}
