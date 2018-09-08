package rs.ac.uns.ftn.sbz.backend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Disease;
import rs.ac.uns.ftn.sbz.backend.model.enumeration.DiseaseGroup;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiseaseDto
{
    private Long id;
    private String name;
    private DiseaseGroup group;
    private List<SymptomDto> genericSymptoms;
    private List<SymptomDto> specificSymptoms;

    public DiseaseDto(Disease disease)
    {
        this.id = disease.getId();
        this.name = disease.getName();
        this.group = disease.getGroup();
        this.genericSymptoms = disease.getGenericSymptoms().stream().map(SymptomDto::new).collect(Collectors.toList());
        this.specificSymptoms = disease.getSpecificSymptoms().stream().map(SymptomDto::new).collect(Collectors.toList());
    }
}
