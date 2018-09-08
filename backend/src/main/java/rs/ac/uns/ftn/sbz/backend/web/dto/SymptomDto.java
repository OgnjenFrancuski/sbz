package rs.ac.uns.ftn.sbz.backend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Symptom;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SymptomDto
{
    private Long id;
    private String name;
    private Double value;

    public SymptomDto(Symptom symptom)
    {
        this.id = symptom.getId();
        this.name = symptom.getName();
    }
}
