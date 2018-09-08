package rs.ac.uns.ftn.sbz.backend.web.util;

import rs.ac.uns.ftn.sbz.backend.model.*;
import rs.ac.uns.ftn.sbz.backend.web.dto.*;

import java.util.HashSet;
import java.util.stream.Collectors;


public class Converter
{
    public static Diagnosis convertToObj(DiagnosisDto dto,
                                         Boolean convertPatientAllergics,
                                         Boolean convertDiseaseSymptoms)
    {
        Diagnosis ret = new Diagnosis();
        ret.setId(dto.getId());
        ret.setPhysician(Converter.convertToObj(dto.getPhysician()));
        ret.setPatient(Converter.convertToObj(dto.getPatient(), convertPatientAllergics, false));
        ret.setDate(dto.getDate());

        ret.setDiseases(dto.getDiseases().stream()
                .map(d -> Converter.convertToObj(d, convertDiseaseSymptoms)).collect(Collectors.toSet()));

        ret.setSymptoms(dto.getSymptoms().stream()
                .map(Converter::convertToObj).collect(Collectors.toSet()));

        ret.setPrescribedMedications(dto.getPrescribedMedications().stream()
                .map(Converter::convertToObj).collect(Collectors.toSet()));

        ret.setBodyTemperature(dto.getBodyTemperature());


        return ret;
    }


    private static Medication convertToObj(MedicationDto dto)
    {
        Medication ret = new Medication();

        ret.setType(dto.getType());
        ret.setName(dto.getName());
        ret.setId(dto.getId());
        ret.setIngredients(dto.getIngredients().stream()
                .map(Converter::convertToObj).collect(Collectors.toSet()));

        return ret;
    }


    public static Disease convertToObj(DiseaseDto dto, Boolean convertDiseaseSymptoms)
    {
        Disease ret = new Disease();
        ret.setId(dto.getId());
        ret.setName(dto.getName());
        ret.setGroup(dto.getGroup());

        if (convertDiseaseSymptoms)
        {
            ret.setGenericSymptoms(dto.getGenericSymptoms().stream()
                    .map(Converter::convertToObj).collect(Collectors.toList()));

            ret.setSpecificSymptoms(dto.getSpecificSymptoms().stream()
                    .map(Converter::convertToObj).collect(Collectors.toList()));
        }

        return ret;
    }


    public static Ingredient convertToObj(IngredientDto dto)
    {
        return new Ingredient(dto.getId(), dto.getName());
    }


    public static Medication convertToObj(MedicationDto dto, Boolean convertIngredients)
    {
        Medication ret = new Medication(dto.getId(), dto.getName(), dto.getType(), new HashSet<>());

        if (convertIngredients && dto.getIngredients() != null)
            ret.getIngredients().addAll(dto.getIngredients().stream().map(Converter::convertToObj).collect(Collectors.toSet()));

        return ret;
    }


    public static Patient convertToObj(PatientDto dto, Boolean convertPatientAllergics, Boolean convertPatientDiagnoses)
    {
        Patient ret = new Patient(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getPersonalId(), dto.getHealthCardId(), new HashSet<>(), new HashSet<>());

        if (convertPatientAllergics && dto.getAllergicIngredients() != null)
            ret.getAllergicIngredients().addAll(dto.getAllergicIngredients().stream().map(Converter::convertToObj).collect(Collectors.toSet()));

        if (convertPatientDiagnoses && dto.getDiagnoses() != null)
            ret.getDiagnoses().addAll(dto.getDiagnoses().stream().map(Converter::convertToObj).collect(Collectors.toSet()));

        return ret;
    }


    public static Diagnosis convertToObj(DiagnosisDto dto)
    {
        Diagnosis ret = new Diagnosis(
                dto.getId(),
                Converter.convertToObj(dto.getPhysician()),
                Converter.convertToObj(dto.getPatient(), true, false),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                dto.getBodyTemperature(),
                dto.getDate());

        ret.getSymptoms().addAll(dto.getSymptoms().stream().map(Converter::convertToObj).collect(Collectors.toSet()));
        ret.getDiseases().addAll(dto.getDiseases().stream().map((DiseaseDto d) -> Converter.convertToObj(d, true)).collect(Collectors.toSet()));
        ret.getPrescribedMedications().addAll(dto.getPrescribedMedications().stream().map((MedicationDto m) -> Converter.convertToObj(m, true)).collect(Collectors.toSet()));

        return ret;
    }


    public static Physician convertToObj(PhysicianDto dto)
    {
        return new Physician(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getPersonalId());
    }


    public static Symptom convertToObj(SymptomDto dto)
    {
        return new Symptom(dto.getId(), dto.getName());
    }
}
