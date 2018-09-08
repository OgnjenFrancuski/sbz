package rs.ac.uns.ftn.sbz.backend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Ingredient;
import rs.ac.uns.ftn.sbz.backend.model.Patient;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDto
{
    private Long id;
    private String firstName;
    private String lastName;
    private String personalId;
    private String healthCardId;
    private Set<IngredientDto> allergicIngredients;
    private Set<DiagnosisDto> diagnoses;

    public PatientDto(Patient patient, Boolean convertDiagnosis)
    {
        this.id = patient.getId();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.personalId = patient.getPersonalId();
        this.healthCardId = patient.getHealthCardId();
        this.allergicIngredients = patient.getAllergicIngredients().stream().map(IngredientDto::new).collect(Collectors.toSet());

        if (convertDiagnosis)
            this.diagnoses = patient.getDiagnoses().stream().map(DiagnosisDto::new).collect(Collectors.toSet());
    }
}
