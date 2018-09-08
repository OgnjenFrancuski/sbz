package rs.ac.uns.ftn.sbz.backend.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Ingredient;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientDto
{
    private Long id;
    private String name;


    public IngredientDto(Ingredient ingredient)
    {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
    }
}
