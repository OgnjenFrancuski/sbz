package rs.ac.uns.ftn.sbz.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbz.backend.model.Diagnosis;
import rs.ac.uns.ftn.sbz.backend.model.Medication;
import rs.ac.uns.ftn.sbz.backend.service.DiagnosisService;
import rs.ac.uns.ftn.sbz.backend.web.dto.DiagnosisDto;
import rs.ac.uns.ftn.sbz.backend.web.dto.MedicationDto;
import rs.ac.uns.ftn.sbz.backend.web.util.Converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/diagnosis")
public class DiagnosisController
{
    private final DiagnosisService diagnosisService;


    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService)
    {
        this.diagnosisService = diagnosisService;
    }




    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_PHYSICIAN')")
    public ResponseEntity create(@RequestBody DiagnosisDto dto)
    {
        Diagnosis ret = Converter.convertToObj(dto, false, false);
        ret = this.diagnosisService.create(ret);
        return new ResponseEntity<>(new DiagnosisDto(ret), HttpStatus.CREATED);
    }




    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity update(@RequestBody DiagnosisDto dto)
    {
        Diagnosis ret = Converter.convertToObj(dto, false, false);
        ret = this.diagnosisService.update(ret);
        return new ResponseEntity<>(new DiagnosisDto(ret), HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        this.diagnosisService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        Diagnosis ret = this.diagnosisService.getOne(id);
        return new ResponseEntity<>(new DiagnosisDto(ret), HttpStatus.OK);
    }




    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAll()
    {
        Set<Diagnosis> diagnoses = this.diagnosisService.getAll();
        Set<DiagnosisDto> ret = diagnoses.stream().map(DiagnosisDto::new).collect(Collectors.toSet());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
