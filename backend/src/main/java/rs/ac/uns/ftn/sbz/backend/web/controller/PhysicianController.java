package rs.ac.uns.ftn.sbz.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbz.backend.model.Physician;
import rs.ac.uns.ftn.sbz.backend.service.PhysicianService;
import rs.ac.uns.ftn.sbz.backend.web.dto.PhysicianDto;
import rs.ac.uns.ftn.sbz.backend.web.util.Converter;

import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/physicians")
public class PhysicianController
{
    private final PhysicianService physicianService;


    @Autowired
    public PhysicianController(PhysicianService physicianService)
    {
        this.physicianService = physicianService;
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity create(@RequestBody PhysicianDto dto)
    {
        Physician ret = Converter.convertToObj(dto);
        ret = this.physicianService.create(ret);
        return new ResponseEntity<>(new PhysicianDto(ret), HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity update(@RequestBody PhysicianDto dto)
    {
        Physician ret = Converter.convertToObj(dto);
        ret = this.physicianService.update(ret);
        return new ResponseEntity<>(new PhysicianDto(ret), HttpStatus.CREATED);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        this.physicianService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getOne(@PathVariable("id") Long id)
    {
        Physician ret = this.physicianService.getOne(id);
        return new ResponseEntity<>(new PhysicianDto(ret), HttpStatus.OK);
    }




    @RequestMapping(
            value = "/my/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("@securityService.hasAccountSpecificAuthorities(#username)")
    public ResponseEntity getOneByUsername(@PathVariable("username") String username)
    {
        Physician ret = this.physicianService.getByUsername();
        return new ResponseEntity<>(new PhysicianDto(ret), HttpStatus.OK);
    }




    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAll()
    {
        Set<Physician> ingredients = this.physicianService.getAll();
        Set<PhysicianDto> ret = ingredients.stream().map(PhysicianDto::new).collect(Collectors.toSet());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
