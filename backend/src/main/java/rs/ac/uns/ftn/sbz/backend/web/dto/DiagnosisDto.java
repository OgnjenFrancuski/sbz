package rs.ac.uns.ftn.sbz.backend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Diagnosis;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DiagnosisDto
{
    private Long id;
    private PhysicianDto physician;
    private PatientDto patient;
    private Set<SymptomDto> symptoms;
    private Set<DiseaseDto> diseases;
    private Set<MedicationDto> prescribedMedications;
    private Double bodyTemperature;
    private Date date;


    public DiagnosisDto(Diagnosis diagnosis)
    {
        this.id = diagnosis.getId();
        this.patient = new PatientDto(diagnosis.getPatient(), false);
        this.physician = new PhysicianDto(diagnosis.getPhysician());
        this.symptoms = diagnosis.getSymptoms().stream().map(SymptomDto::new).collect(Collectors.toSet());
        this.diseases = diagnosis.getDiseases().stream().map(DiseaseDto::new).collect(Collectors.toSet());
        this.prescribedMedications = diagnosis.getPrescribedMedications().stream().map(MedicationDto::new).collect(Collectors.toSet());
        this.bodyTemperature = diagnosis.getBodyTemperature();
        this.date = diagnosis.getDate();
    }
}
