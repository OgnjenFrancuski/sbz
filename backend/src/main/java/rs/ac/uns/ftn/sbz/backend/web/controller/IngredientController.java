package rs.ac.uns.ftn.sbz.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbz.backend.model.Ingredient;
import rs.ac.uns.ftn.sbz.backend.service.IngredientService;
import rs.ac.uns.ftn.sbz.backend.web.dto.IngredientDto;
import rs.ac.uns.ftn.sbz.backend.web.util.Converter;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/ingredients")
public class IngredientController
{
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService)
    {
        this.ingredientService = ingredientService;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity create(@RequestBody IngredientDto dto)
    {
        Ingredient ret = Converter.convertToObj(dto);
        ret = this.ingredientService.create(ret);
        return new ResponseEntity<>(new IngredientDto(ret), HttpStatus.CREATED);
    }




    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity update(@RequestBody IngredientDto dto)
    {
        Ingredient ret = Converter.convertToObj(dto);
        ret = this.ingredientService.update(ret);
        return new ResponseEntity<>(new IngredientDto(ret), HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        this.ingredientService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        Ingredient ret = this.ingredientService.getOne(id);
        return new ResponseEntity<>(new IngredientDto(ret), HttpStatus.OK);
    }




    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAll()
    {
        Set<Ingredient> ingredients = this.ingredientService.getAll();
        Set<IngredientDto> ret = ingredients.stream().map(IngredientDto::new).collect(Collectors.toSet());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
