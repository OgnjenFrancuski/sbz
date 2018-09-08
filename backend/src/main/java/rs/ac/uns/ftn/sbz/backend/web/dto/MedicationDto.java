package rs.ac.uns.ftn.sbz.backend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Medication;
import rs.ac.uns.ftn.sbz.backend.model.enumeration.MedicationType;

import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicationDto
{
    private Long id;
    private String name;
    private MedicationType type;
    private Set<IngredientDto> ingredients;

    public MedicationDto(Medication medication)
    {
        this.id = medication.getId();
        this.name = medication.getName();
        this.type = medication.getType();
        this.ingredients = medication.getIngredients().stream().map(IngredientDto::new).collect(Collectors.toSet());
    }
}
