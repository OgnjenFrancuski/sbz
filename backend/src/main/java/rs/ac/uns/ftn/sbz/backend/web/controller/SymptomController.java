package rs.ac.uns.ftn.sbz.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbz.backend.model.Symptom;
import rs.ac.uns.ftn.sbz.backend.service.SymptomService;
import rs.ac.uns.ftn.sbz.backend.web.dto.SymptomDto;
import rs.ac.uns.ftn.sbz.backend.web.util.Converter;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/symptoms")
public class SymptomController
{
    private final SymptomService symptomService;


    @Autowired
    public SymptomController(SymptomService symptomService)
    {
        this.symptomService = symptomService;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity create(@RequestBody SymptomDto dto)
    {
        Symptom ret = Converter.convertToObj(dto);
        ret = this.symptomService.create(ret);
        return new ResponseEntity<>(new SymptomDto(ret), HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity update(@RequestBody SymptomDto dto)
    {
        Symptom ret = Converter.convertToObj(dto);
        ret = this.symptomService.update(ret);
        return new ResponseEntity<>(new SymptomDto(ret), HttpStatus.CREATED);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        this.symptomService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        Symptom ret = this.symptomService.getOne(id);
        return new ResponseEntity<>(new SymptomDto(ret), HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAll()
    {
        Set<Symptom> medications = this.symptomService.getAll();
        Set<SymptomDto> ret = medications.stream().map(SymptomDto::new).collect(Collectors.toSet());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
