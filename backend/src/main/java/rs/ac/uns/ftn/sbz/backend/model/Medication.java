package rs.ac.uns.ftn.sbz.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.enumeration.MedicationType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medication")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medication_id")
    private Long id;

    @Column(name = "medication_name", unique = true)
    private String name;

    @Column(name = "medication_type")
    @Enumerated(EnumType.STRING)
    private MedicationType type;

    @ManyToMany
    @JoinTable(name = "medication_ingredient",
               joinColumns = { @JoinColumn(name = "medication_id") },
               inverseJoinColumns = { @JoinColumn(name = "ingredient_id") })
    private Set<Ingredient> ingredients;


    public Boolean hasIngredient(Ingredient ingredient)
    {
        return this.ingredients.contains(ingredient);
    }
}
