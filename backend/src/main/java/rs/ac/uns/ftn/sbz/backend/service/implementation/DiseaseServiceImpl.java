package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.exception.NotFoundException;
import rs.ac.uns.ftn.sbz.backend.model.Disease;
import rs.ac.uns.ftn.sbz.backend.model.Symptom;
import rs.ac.uns.ftn.sbz.backend.repository.DiseaseRepository;
import rs.ac.uns.ftn.sbz.backend.repository.SymptomRepository;
import rs.ac.uns.ftn.sbz.backend.service.DiseaseService;

import java.util.*;


@Service
public class DiseaseServiceImpl implements DiseaseService
{
    private final DiseaseRepository diseaseRepository;
    private final SymptomRepository symptomRepository;


    @Autowired
    public DiseaseServiceImpl(DiseaseRepository diseaseRepository,
                              SymptomRepository symptomRepository)
    {
        this.diseaseRepository = diseaseRepository;
        this.symptomRepository = symptomRepository;
    }


    @Override
    public Disease create(Disease disease)
    {
        Disease newDisease = new Disease();
        newDisease.setGroup(disease.getGroup());
        newDisease.setName(disease.getName());

        List<Symptom> specificSymptoms = new ArrayList<>();
        for (Symptom symptom : disease.getSpecificSymptoms())
        {
            Symptom specificSymptom = this.symptomRepository.findById(symptom.getId()).orElseThrow(NotFoundException::new);
            specificSymptoms.add(specificSymptom);
        }

        List<Symptom> genericSymptoms = new ArrayList<>();
        for (Symptom symptom : disease.getGenericSymptoms())
        {
            Symptom genericSymptom = this.symptomRepository.findById(symptom.getId()).orElseThrow(NotFoundException::new);
            genericSymptoms.add(genericSymptom);
        }

        newDisease.setSpecificSymptoms(specificSymptoms);
        newDisease.setGenericSymptoms(genericSymptoms);

        return this.diseaseRepository.save(newDisease);
    }


    @Override
    public Disease update(Disease disease)
    {
        Disease diseaseDB = this.diseaseRepository.findById(disease.getId()).orElseThrow(NotFoundException::new);

        diseaseDB.setName(disease.getName());
        diseaseDB.setGroup(disease.getGroup());

        List<Symptom> specificSymptoms = new ArrayList<>();
        for (Symptom symptom : disease.getSpecificSymptoms())
        {
            Symptom specificSymptom = this.symptomRepository.findById(symptom.getId()).orElseThrow(NotFoundException::new);
            specificSymptoms.add(specificSymptom);
        }

        List<Symptom> genericSymptoms = new ArrayList<>();
        for (Symptom symptom : disease.getGenericSymptoms())
        {
            Symptom genericSymptom = this.symptomRepository.findById(symptom.getId()).orElseThrow(NotFoundException::new);
            genericSymptoms.add(genericSymptom);
        }

        diseaseDB.setSpecificSymptoms(specificSymptoms);
        diseaseDB.setGenericSymptoms(genericSymptoms);

        return this.diseaseRepository.save(diseaseDB);
    }


    @Override
    public void delete(Long id)
    {
        this.diseaseRepository.deleteById(id);
    }


    @Override
    public Disease getOne(Long id)
    {
        return this.diseaseRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @Override
    public Set<Disease> getAll()
    {
        return new HashSet<>(this.diseaseRepository.findAll());
    }


//    @Override
//    public Map<Disease, Integer> findDiseasesThatContainSymptoms(List<Symptom> symptoms)
//    {
//        List<Disease> diseases = this.diseaseRepository.findAll();
//        Map<Disease, Integer> diseaseSymptoms = new HashMap<>();
//
//        for (Disease disease : diseases)
//        {
//            Integer count = 0;
//
//            for (Symptom symptom : symptoms)
//                if (disease.hasSymptom(symptom))
//                    count++;
//
//            if (count > 0)
//                diseaseSymptoms.put(disease, count);
//        }
//
//        return diseaseSymptoms;
//    }

}
