package rs.ac.uns.ftn.sbz.backend.service;

import rs.ac.uns.ftn.sbz.backend.model.Ingredient;

import java.util.Set;


public interface IngredientService
{
    Ingredient create(Ingredient ingredient);

    Ingredient update(Ingredient ingredient);

    void delete(Long id);

    Ingredient getOne(Long id);

    Set<Ingredient> getAll();
}
