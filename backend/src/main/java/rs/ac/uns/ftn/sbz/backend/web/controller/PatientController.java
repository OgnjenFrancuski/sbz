package rs.ac.uns.ftn.sbz.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbz.backend.model.Patient;
import rs.ac.uns.ftn.sbz.backend.service.PatientService;
import rs.ac.uns.ftn.sbz.backend.web.dto.PatientDto;
import rs.ac.uns.ftn.sbz.backend.web.util.Converter;

import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/patients")
public class PatientController
{
    private final PatientService patientService;


    @Autowired
    public PatientController(PatientService patientService)
    {
        this.patientService = patientService;
    }




    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity create(@RequestBody PatientDto dto)
    {
        Patient ret = Converter.convertToObj(dto, true, false);
        ret = this.patientService.create(ret);
        return new ResponseEntity<>(new PatientDto(ret, false), HttpStatus.CREATED);
    }




    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity update(@RequestBody PatientDto dto)
    {
        Patient ret = Converter.convertToObj(dto, true, false);
        ret = this.patientService.update(ret);
        return new ResponseEntity<>(new PatientDto(ret, false), HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        this.patientService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        Patient ret = this.patientService.getOne(id);
        return new ResponseEntity<>(new PatientDto(ret, false), HttpStatus.OK);
    }




    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAll()
    {
        Set<Patient> patients = this.patientService.getAll();
        Set<PatientDto> ret = patients.stream().map((p) -> new PatientDto(p, false)).collect(Collectors.toSet());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
