package rs.ac.uns.ftn.sbz.backend.web.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.sbz.backend.model.Diagnosis;
import rs.ac.uns.ftn.sbz.backend.model.Disease;
import rs.ac.uns.ftn.sbz.backend.model.Medication;
import rs.ac.uns.ftn.sbz.backend.model.Patient;
import rs.ac.uns.ftn.sbz.backend.service.KieService;
import rs.ac.uns.ftn.sbz.backend.web.dto.*;
import rs.ac.uns.ftn.sbz.backend.web.util.Converter;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/kie")
public class KieController
{

    private final KieService kieService;
    private final KieContainer kieContainer;

    @Value("${ruleSessionName}")
    private String ruleSessionName;

    @Value("${reportSessionName}")
    private String reportSessionName;


    @Autowired
    public KieController(KieService kieService, KieContainer kieContainer)
    {
        this.kieService = kieService;
        this.kieContainer = kieContainer;
    }


    @RequestMapping(
            value = "/diagnostics/run",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity runDiagnostics(HttpSession httpSession, @RequestBody DiagnosisDto dto)
    {
        Diagnosis diagnosis = Converter.convertToObj(dto);
        KieSession ks = (KieSession) httpSession.getAttribute("kieSession");
        List<Disease> ret = this.kieService.performDiagnosis(ks, diagnosis);
        List<DiseaseDto> retDto = ret.stream().map(DiseaseDto::new).collect(Collectors.toList());
        this.resetKieSession(httpSession);
        return new ResponseEntity<>(retDto, HttpStatus.OK);
    }


    @RequestMapping(
            value = "diagnostics/validation",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity validateDiagnostics(HttpSession httpSession, @RequestBody DiagnosisDto dto)
    {
        Diagnosis ret = Converter.convertToObj(dto);
        KieSession ks = (KieSession) httpSession.getAttribute("kieSession");
        List<Medication> allergies = this.kieService.validateDiagnosis(ks, ret);
        List<MedicationDto> retDto = allergies.stream().map(MedicationDto::new).collect(Collectors.toList());
        this.resetKieSession(httpSession);
        return new ResponseEntity<>(retDto, HttpStatus.OK);
    }


    @RequestMapping(
            value = "diagnostics/symptom-order-of-disease",
            method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getSymptomsSortedByImportance(HttpSession httpSession, @RequestBody String diseaseName)
    {
        KieSession ks = (KieSession) httpSession.getAttribute("kieSession");
        Disease ret = this.kieService.getSymptomsSortedByImportance(ks, diseaseName);
        this.resetKieSession(httpSession);
        return new ResponseEntity<>(new DiseaseDto(ret), HttpStatus.OK);

    }


    @RequestMapping(
            value = "diagnostics/disease-ranking",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getDiseaseRanking(HttpSession httpSession, @RequestBody DiagnosisDto dto)
    {
        Diagnosis diagnosis = Converter.convertToObj(dto, false, true);
        KieSession ks = (KieSession) httpSession.getAttribute("kieSession");
        Map<Disease, Integer> ret = this.kieService.getDiseaseRanking(ks, diagnosis);
        List<DiseaseRankDto> retDto = new ArrayList<>();

        for (Disease d : ret.keySet())
            retDto.add(new DiseaseRankDto(new DiseaseDto(d), ret.get(d)));

        this.resetKieSession(httpSession);
        return new ResponseEntity<>(retDto, HttpStatus.OK);
    }


    @RequestMapping(
            value = "diagnostics/chronical",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity possibleChronicDiseasePatients()
    {
        KieSession kieSession = this.kieContainer.newKieSession(this.reportSessionName);
        List<Patient> ret = this.kieService.possibleChronicDiseasePatients(kieSession);
        kieSession.destroy();
        List<PatientDto> dto = ret.stream().map((p) -> new PatientDto(p, false)).collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @RequestMapping(
            value = "diagnostics/addicts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity possibleAddictedPatients()
    {
        KieSession kieSession = this.kieContainer.newKieSession(this.reportSessionName);
        List<Patient> ret = this.kieService.possibleAddictPatients(kieSession);
        kieSession.destroy();
        List<PatientDto> dto = ret.stream().map((p) -> new PatientDto(p, false)).collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @RequestMapping(
            value = "diagnostics/weak-immune-system",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity weakImmuneSystemPatients()
    {
        KieSession kieSession = this.kieContainer.newKieSession(this.reportSessionName);
        List<Patient> ret = this.kieService.weakImmuneSystemPatients(kieSession);
        kieSession.destroy();
        List<PatientDto> dto = ret.stream().map((p) -> new PatientDto(p, false)).collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    private void resetKieSession(HttpSession httpSession)
    {
        KieSession oldKieSession = (KieSession) httpSession.getAttribute("kieSession");
        oldKieSession.dispose();
        KieSession kieSession = this.kieContainer.newKieSession(this.ruleSessionName);
        httpSession.setAttribute("kieSession", kieSession);
    }
}
