package rs.ac.uns.ftn.sbz.backend.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseRankDto
{
    private DiseaseDto disease;
    private Integer count;
}
