package rs.ac.uns.ftn.sbz.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(name = "ingredient_name", unique = true)
    private String name;


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;

        Ingredient other = (Ingredient) o;

        if (id != null && !id.equals(other.id)) return false;
        if (name != null && !name.equalsIgnoreCase(other.name)) return false;

        return true;
    }
}
