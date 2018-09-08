package rs.ac.uns.ftn.sbz.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbz.backend.model.Medication;
import rs.ac.uns.ftn.sbz.backend.service.MedicationService;
import rs.ac.uns.ftn.sbz.backend.web.dto.MedicationDto;
import rs.ac.uns.ftn.sbz.backend.web.util.Converter;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/medications")
public class MedicationController
{
    private final MedicationService medicationService;


    @Autowired
    public MedicationController(MedicationService medicationService)
    {
        this.medicationService = medicationService;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity create(@RequestBody MedicationDto dto)
    {
        Medication ret = Converter.convertToObj(dto, true);
        ret = this.medicationService.create(ret);
        return new ResponseEntity<>(new MedicationDto(ret), HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity update(@RequestBody MedicationDto dto)
    {
        Medication ret = Converter.convertToObj(dto, true);
        ret = this.medicationService.update(ret);
        return new ResponseEntity<>(new MedicationDto(ret), HttpStatus.CREATED);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        this.medicationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        Medication ret = this.medicationService.getOne(id);
        return new ResponseEntity<>(new MedicationDto(ret), HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAll()
    {
        Set<Medication> medications = this.medicationService.getAll();
        Set<MedicationDto> ret = medications.stream().map(MedicationDto::new).collect(Collectors.toSet());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
