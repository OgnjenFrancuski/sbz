package rs.ac.uns.ftn.sbz.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends Person
{
    @Column(name = "patient_health_card_id")
    private String healthCardId;

    @ManyToMany
    @JoinTable(name = "patient_allergic_ingredients",
               joinColumns = { @JoinColumn(name = "person_id") },
               inverseJoinColumns = { @JoinColumn(name = "ingredient_id") })
    private Set<Ingredient> allergicIngredients;

    @OneToMany(mappedBy = "patient")
    private Set<Diagnosis> diagnoses;


    public Patient(Long id, String firstName, String lastName, String personalId, String healthCardId, HashSet<Ingredient> allergicIngredients, HashSet<Diagnosis> diagnoses)
    {
        super(id, firstName, lastName, personalId);

        this.setHealthCardId(healthCardId);
        this.setAllergicIngredients(allergicIngredients);
        this.setDiagnoses(diagnoses);
    }
}
