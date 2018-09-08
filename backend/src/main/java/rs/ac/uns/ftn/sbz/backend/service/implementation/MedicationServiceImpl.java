package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.exception.ConflictException;
import rs.ac.uns.ftn.sbz.backend.exception.NotFoundException;
import rs.ac.uns.ftn.sbz.backend.model.Ingredient;
import rs.ac.uns.ftn.sbz.backend.model.Medication;
import rs.ac.uns.ftn.sbz.backend.repository.IngredientRepository;
import rs.ac.uns.ftn.sbz.backend.repository.MedicationRepository;
import rs.ac.uns.ftn.sbz.backend.service.MedicationService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MedicationServiceImpl implements MedicationService
{
    private final IngredientRepository ingredientRepository;
    private final MedicationRepository medicationRepository;


    @Autowired
    public MedicationServiceImpl(IngredientRepository ingredientRepository, MedicationRepository medicationRepository)
    {
        this.ingredientRepository = ingredientRepository;
        this.medicationRepository = medicationRepository;
    }


    @Override
    public Medication create(Medication medication)
    {
        if (medicationRepository.findByName(medication.getName()).isPresent())
            throw new ConflictException();

        Set<Ingredient> ingredients = medication.getIngredients().stream().map(i -> this.ingredientRepository.findById(i.getId())
                .orElseThrow(NotFoundException::new)).collect(Collectors.toSet());

        Medication newMedication = new Medication(null, medication.getName(), medication.getType(), ingredients);

        return this.medicationRepository.save(newMedication);
    }


    @Override
    public Medication update(Medication medication)
    {
        Medication medicationDB = this.medicationRepository.findById(medication.getId()).orElseThrow(NotFoundException::new);

        medicationDB.setName(medication.getName());
        medicationDB.setType(medication.getType());

        Set<Ingredient> ingredients = medication.getIngredients().stream().map(i -> this.ingredientRepository.findById(i.getId())
                .orElseThrow(NotFoundException::new)).collect(Collectors.toSet());

        medicationDB.setIngredients(ingredients);

        return this.medicationRepository.save(medicationDB);
    }


    @Override
    public void delete(Long id)
    {
        this.medicationRepository.deleteById(id);
    }


    @Override
    public Medication getOne(Long id)
    {
        return this.medicationRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @Override
    public Set<Medication> getAll()
    {
        return new HashSet<>(this.medicationRepository.findAll());
    }
}
