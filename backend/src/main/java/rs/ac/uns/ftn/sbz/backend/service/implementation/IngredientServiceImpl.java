package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.exception.ConflictException;
import rs.ac.uns.ftn.sbz.backend.exception.NotFoundException;
import rs.ac.uns.ftn.sbz.backend.model.Ingredient;
import rs.ac.uns.ftn.sbz.backend.repository.IngredientRepository;
import rs.ac.uns.ftn.sbz.backend.service.IngredientService;

import java.util.HashSet;
import java.util.Set;


@Service
public class IngredientServiceImpl implements IngredientService
{
    private final IngredientRepository ingredientRepository;


    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository)
    {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public Ingredient create(Ingredient ingredient)
    {
        if (this.ingredientRepository.findByName(ingredient.getName()).isPresent())
            throw new ConflictException();
        return this.ingredientRepository.save(ingredient);
    }


    @Override
    public Ingredient update(Ingredient ingredient)
    {
        Ingredient ingredientDB = this.ingredientRepository.findById(ingredient.getId()).orElseThrow(NotFoundException::new);
        ingredientDB.setName(ingredient.getName());
        return this.ingredientRepository.save(ingredientDB);
    }


    @Override
    public void delete(Long id)
    {
        this.ingredientRepository.deleteById(id);
    }


    @Override
    public Ingredient getOne(Long id)
    {
        return this.ingredientRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @Override
    public Set<Ingredient> getAll()
    {
        return new HashSet<>(this.ingredientRepository.findAll());
    }
}
