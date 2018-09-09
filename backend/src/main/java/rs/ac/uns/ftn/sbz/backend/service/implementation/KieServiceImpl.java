package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.model.*;
import rs.ac.uns.ftn.sbz.backend.model.util.*;
import rs.ac.uns.ftn.sbz.backend.service.*;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KieServiceImpl implements KieService
{
    private final KieContainer kieContainer;
    private final PatientService patientService;
    private final DiagnosisService diagnosisService;
    private final DiseaseService diseaseService;
    private final SymptomService symptomService;
    private final MedicationService medicationService;
    private final IngredientService ingredientService;


    @Autowired
    public KieServiceImpl(KieContainer kieContainer, PatientService patientService, DiagnosisService diagnosisService, DiseaseService diseaseService, SymptomService symptomService, MedicationService medicationService, IngredientService ingredientService)
    {
        this.kieContainer = kieContainer;
        this.patientService = patientService;
        this.diagnosisService = diagnosisService;
        this.diseaseService = diseaseService;
        this.symptomService = symptomService;
        this.medicationService = medicationService;
        this.ingredientService = ingredientService;
    }


    @Override
    public List<Disease> performDiagnosis(KieSession kieSession, Diagnosis diagnosis)
    {
        Set<Symptom> symptoms = diagnosis.getSymptoms().stream().map(i -> this.symptomService.getOne(i.getId())).collect(Collectors.toSet());
        Patient patient = this.patientService.getOne(diagnosis.getPatient().getId());

        diagnosis.setSymptoms(symptoms);
        diagnosis.setPatient(patient);
        diagnosis.setDiseases(new HashSet<>());
        diagnosis.setDate(new Date((Calendar.getInstance().getTime().getTime())));

        kieSession.insert(diagnosis);
        kieSession.insert(diagnosis.getPatient());
        this.symptomService.getAll().forEach(kieSession::insert);
        this.diseaseService.getAll().forEach(kieSession::insert);

        kieSession.getAgenda().getAgendaGroup("DISEASE-DIAGNOSTIC").setFocus();
        kieSession.fireAllRules();

        return new ArrayList<>(diagnosis.getDiseases());
    }


    @Override
    public List<Medication> validateDiagnosis(KieSession kieSession, Diagnosis diagnosis)
    {
        diagnosis.setPrescribedMedications(diagnosis.getPrescribedMedications().stream()
                .map((pm) -> this.medicationService.getOne(pm.getId()))
                .collect(Collectors.toSet()));
        diagnosis.setPatient(this.patientService.getOne(diagnosis.getPatient().getId()));
        diagnosis.setDate(new Date((Calendar.getInstance().getTime().getTime())));

        MedicationList ml = new MedicationList();

        kieSession.insert(ml);
        kieSession.insert(diagnosis);
        kieSession.insert(diagnosis.getPatient());

        kieSession.getAgenda().getAgendaGroup("ALLERGIES").setFocus();
        kieSession.fireAllRules();

        return ml.getMedications();
    }


    @Override
    public Disease getSymptomsSortedByImportance(KieSession kieSession, String diseaseName)
    {
        Disease disease = new Disease();
        disease.setName(diseaseName);
        disease.setGenericSymptoms(new ArrayList<>());
        disease.setSpecificSymptoms(new ArrayList<>());

        kieSession.insert(disease);
        kieSession.insert(this.symptomService);

        kieSession.getAgenda().getAgendaGroup("DISEASE-SYMPTOMS").setFocus();
        kieSession.fireAllRules();

        return disease;
    }


    @Override
    public Map<Disease, Integer> getDiseaseRanking(KieSession kieSession, Diagnosis diagnosis)
    {
        DiseaseMap dm = new DiseaseMap(new HashMap<>());

        Set<Symptom> symptoms = diagnosis.getSymptoms().stream()
                .map(i -> this.symptomService.getOne(i.getId()))
                .collect(Collectors.toSet());
        diagnosis.setSymptoms(symptoms);
        diagnosis.setDate(new Date((Calendar.getInstance().getTime().getTime())));

        this.diseaseService.getAll().forEach(kieSession::insert);

        kieSession.insert(diagnosis);
        kieSession.insert(dm);

        kieSession.getAgenda().getAgendaGroup("DISEASE-MATCHED-SYMPTOMS-COUNT").setFocus();
        kieSession.fireAllRules();

        return dm.getMap();
    }


    @Override
    public List<Patient> possibleChronicDiseasePatients(KieSession kieSession)
    {

        PatientList pl = new PatientList();
        DiagnosisList dl = new DiagnosisList();

        this.patientService.getAll().forEach(kieSession::insert);
        this.diagnosisService.getAll().forEach(dl::add);

        kieSession.insert(pl);
        kieSession.insert(dl);
        kieSession.insert(new Report(ReportType.CHRONIC));
        kieSession.getAgenda().getAgendaGroup("CHRONIC").setFocus();
        kieSession.fireAllRules();

        return pl.getPatients();
    }


    @Override
    public List<Patient> possibleAddictPatients(KieSession kieSession)
    {
        PatientList pl = new PatientList();
        DiagnosisList dl = new DiagnosisList();

        this.patientService.getAll().forEach(kieSession::insert);
        this.diagnosisService.getAll().forEach(dl::add);

        kieSession.insert(pl);
        kieSession.insert(dl);
        kieSession.insert(new Report(ReportType.ADDICTS));

        kieSession.getAgenda().getAgendaGroup("ADDICTS").setFocus();
        kieSession.fireAllRules();

        return pl.getPatients();
    }


    @Override
    public List<Patient> weakImmuneSystemPatients(KieSession kieSession)
    {

        DiagnosisList dl = new DiagnosisList();
        PatientList pl = new PatientList();

        this.diagnosisService.getAll().forEach(dl::add);
        this.patientService.getAll().forEach(kieSession::insert);

        kieSession.insert(pl);
        kieSession.insert(dl);
        kieSession.insert(new Report(ReportType.WEAK_IMMUNE_SYSTEM));

        kieSession.getAgenda().getAgendaGroup("WEAK_IMMUNE_SYSTEM").setFocus();
        kieSession.fireAllRules();

        return pl.getPatients();
    }
}
