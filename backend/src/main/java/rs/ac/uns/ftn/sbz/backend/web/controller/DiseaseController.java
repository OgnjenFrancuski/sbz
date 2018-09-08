package rs.ac.uns.ftn.sbz.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbz.backend.model.Disease;
import rs.ac.uns.ftn.sbz.backend.service.DiseaseService;
import rs.ac.uns.ftn.sbz.backend.web.dto.DiseaseDto;
import rs.ac.uns.ftn.sbz.backend.web.util.Converter;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/diseases")
public class DiseaseController
{
    private final DiseaseService diseaseService;


    @Autowired
    public DiseaseController(DiseaseService diseaseService)
    {
        this.diseaseService = diseaseService;
    }




    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity create(@RequestBody DiseaseDto dto)
    {
        Disease ret = Converter.convertToObj(dto, true);
        ret = this.diseaseService.create(ret);
        return new ResponseEntity<>(new DiseaseDto(ret), HttpStatus.CREATED);
    }




    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity update(@RequestBody DiseaseDto dto)
    {
        Disease ret = Converter.convertToObj(dto, true);
        ret = this.diseaseService.update(ret);
        return new ResponseEntity<>(new DiseaseDto(ret), HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        this.diseaseService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        Disease ret = this.diseaseService.getOne(id);
        return new ResponseEntity<>(new DiseaseDto(ret), HttpStatus.OK);
    }




    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAll()
    {
        Set<Disease> diseases = this.diseaseService.getAll();
        Set<DiseaseDto> ret = diseases.stream().map(DiseaseDto::new).collect(Collectors.toSet());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
