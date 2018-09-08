package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.exception.ConflictException;
import rs.ac.uns.ftn.sbz.backend.exception.NotFoundException;
import rs.ac.uns.ftn.sbz.backend.model.Symptom;
import rs.ac.uns.ftn.sbz.backend.repository.SymptomRepository;
import rs.ac.uns.ftn.sbz.backend.service.SymptomService;

import java.io.NotActiveException;
import java.util.HashSet;
import java.util.Set;


@Service
public class SymptomServiceImpl implements SymptomService
{
    private final SymptomRepository symptomRepository;


    @Autowired
    public SymptomServiceImpl(SymptomRepository symptomRepository)
    {
        this.symptomRepository = symptomRepository;
    }


    @Override
    public Symptom create(Symptom symptom)
    {
        if (this.symptomRepository.findByName(symptom.getName()).isPresent())
            throw new ConflictException();
        return this.symptomRepository.save(symptom);
    }


    @Override
    public Symptom update(Symptom symptom)
    {
        Symptom symptomDB = this.symptomRepository.findById(symptom.getId()).orElseThrow(NotFoundException::new);
        symptomDB.setName(symptom.getName());
        return this.symptomRepository.save(symptomDB);
    }


    @Override
    public void delete(Long id)
    {
        this.symptomRepository.deleteById(id);
    }


    @Override
    public Symptom getOne(Long id)
    {
        return this.symptomRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @Override
    public Set<Symptom> getAll()
    {
        return new HashSet<>(this.symptomRepository.findAll());
    }


    @Override
    public Symptom findByName(String name)
    {
        return this.symptomRepository.findByName(name).orElseThrow(NotFoundException::new);
    }

}
