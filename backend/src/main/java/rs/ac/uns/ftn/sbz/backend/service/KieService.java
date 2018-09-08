package rs.ac.uns.ftn.sbz.backend.service;

import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbz.backend.model.*;

import java.util.List;
import java.util.Map;

public interface KieService
{


    List<Disease> performDiagnosis(KieSession kieSession, Diagnosis diagnosis);

    List<Medication> validateDiagnosis(KieSession kieSession, Diagnosis diagnosis);

    Disease getSymptomsSortedByImportance(KieSession kieSession, String diseaseName);

    Map<Disease, Integer> getDiseaseRanking(KieSession kieSession, Diagnosis diagnosis);

    List<Patient> possibleChronicDiseasePatients(KieSession kieSession);

    List<Patient> possibleAddictPatients(KieSession kieSession);

    List<Patient> weakImmuneSystemPatients(KieSession kieSession);
}
