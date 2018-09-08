package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.exception.BadRequestException;
import rs.ac.uns.ftn.sbz.backend.exception.NotFoundException;
import rs.ac.uns.ftn.sbz.backend.model.*;
import rs.ac.uns.ftn.sbz.backend.repository.*;
import rs.ac.uns.ftn.sbz.backend.service.DiagnosisService;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class DiagnosisServiceImpl implements DiagnosisService
{
    private final DiagnosisRepository diagnosisRepository;
    private final PatientRepository patientRepository;
    private final PhysicianRepository physicianRepository;
    private final DiseaseRepository diseaseRepository;
    private final SymptomRepository symptomRepository;
    private final UserRepository userRepository;
    private final MedicationRepository medicationRepository;


    @Autowired
    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository,
                                PatientRepository patientRepository,
                                PhysicianRepository physicianRepository,
                                DiseaseRepository diseaseRepository,
                                SymptomRepository symptomRepository,
                                UserRepository userRepository,
                                MedicationRepository medicationRepository)
    {
        this.diagnosisRepository = diagnosisRepository;
        this.patientRepository = patientRepository;
        this.physicianRepository = physicianRepository;
        this.diseaseRepository = diseaseRepository;
        this.symptomRepository = symptomRepository;
        this.userRepository = userRepository;
        this.medicationRepository = medicationRepository;
    }


    @Override
    public Diagnosis create(Diagnosis diagnosis)
    {
        Optional<Patient> patient = this.patientRepository.findById(diagnosis.getPatient().getId());

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepository.findByUsername(username);
        Physician physician = user.getPhysician();

        Set<Disease> diseases = diagnosis.getDiseases().stream().map(d -> this.diseaseRepository.findById(d.getId())
                .orElseThrow(NotFoundException::new)).collect(Collectors.toSet());

        Set<Symptom> symptoms = diagnosis.getSymptoms().stream().map(d -> this.symptomRepository.findById(d.getId())
                .orElseThrow(NotFoundException::new)).collect(Collectors.toSet());

        Set<Medication> prescribedMedications = diagnosis.getPrescribedMedications().stream().map(d -> this.medicationRepository.findById(d.getId())
                .orElseThrow(NotFoundException::new)).collect(Collectors.toSet());

        if (!patient.isPresent() || physician == null || diseases.size() < 1)
            throw new BadRequestException();

        Diagnosis newDiagnosis = new Diagnosis();
        newDiagnosis.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        newDiagnosis.setPatient(patient.get());
        newDiagnosis.setPhysician(physician);
        newDiagnosis.setSymptoms(symptoms);
        newDiagnosis.setDiseases(diseases);
        newDiagnosis.setPrescribedMedications(prescribedMedications);
        newDiagnosis.setBodyTemperature(diagnosis.getBodyTemperature());

        return this.diagnosisRepository.save(newDiagnosis);
    }


    @Override
    public Diagnosis update(Diagnosis diagnosis)
    {
        Optional<Patient> patient = this.patientRepository.findById(diagnosis.getPatient().getId());
        Optional<Physician> physician = this.physicianRepository.findById(diagnosis.getPhysician().getId());

        Set<Disease> diseases = diagnosis.getDiseases().stream().map(d -> this.diseaseRepository.findById(d.getId())
                .orElseThrow(NotFoundException::new)).collect(Collectors.toSet());

        Set<Symptom> symptoms = diagnosis.getSymptoms().stream().map(d -> this.symptomRepository.findById(d.getId())
                .orElseThrow(NotFoundException::new)).collect(Collectors.toSet());

        if (!patient.isPresent() || !physician.isPresent() || diseases.size() < 1)
            throw new BadRequestException();

        Diagnosis diagnosisDB = this.diagnosisRepository.findById(diagnosis.getId()).orElseThrow(NotFoundException::new);

        diagnosisDB.setPatient(patient.get());
        diagnosisDB.setPhysician(physician.get());
        diagnosisDB.setSymptoms(symptoms);
        diagnosisDB.setDiseases(diseases);

        return this.diagnosisRepository.save(diagnosisDB);
    }


    @Override
    public void delete(Long id)
    {
        this.diagnosisRepository.deleteById(id);
    }


    @Override
    public Diagnosis getOne(Long id)
    {
        return this.diagnosisRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @Override
    public Set<Diagnosis> getAll()
    {
        return new HashSet<>(this.diagnosisRepository.findAll());
    }


    @Override
    public Set<Diagnosis> getAllByPatientId(Long patientId)
    {
        return this.diagnosisRepository.findAllByPatientId(patientId);
    }


    @Override
    public Set<Diagnosis> getAllByPatientIdAndDiseaseId(Long patientId, Long diseaseId)
    {
        Set<Diagnosis> diagnoses = this.diagnosisRepository.findAllByPatientId(patientId);

        Set<Diagnosis> ret = new HashSet<>();

        for (Diagnosis diagnosis : diagnoses)
            for (Disease disease : diagnosis.getDiseases())
                if (disease.getId().longValue() == diseaseId)
                {
                    ret.add(diagnosis);
                    break;
                }

        return ret;
    }


    @Override
    public Set<Diagnosis> getAllByPhysicianId(Long physicianId)
    {
        return this.diagnosisRepository.findAllByPhysicianId(physicianId);
    }


//    @Override
//    public List<Medication> validate(Diagnosis diagnosis)
//    {
//        Patient patient = this.patientRepository.findById(diagnosis.getPatient().getId()).orElseThrow(NotFoundException::new);
//
//        List<Medication> prescribedMedications = diagnosis.getPrescribedMedications().stream()
//                .map((m) -> this.medicationRepository.findById(m.getId()).orElseThrow(NotFoundException::new))
//                .collect(Collectors.toList());
//
//        List<Medication> allergicToMedication = new ArrayList<>();
//
//        for (Medication m : prescribedMedications)
//            for (Ingredient i : patient.getAllergicIngredients())
//                if (m.hasIngredient(i))
//                    allergicToMedication.add(m);
//
//        return allergicToMedication;
//    }
}
