package rs.ac.uns.ftn.sbz.backend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.enumeration.DiseaseGroup;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "disease")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Disease
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "disease_id")
    private Long id;

    @Column(name = "disease_name", unique = true)
    private String name;

    @Column(name = "disease_group")
    @Enumerated(EnumType.STRING)
    private DiseaseGroup group;

    @ManyToMany
    @JoinTable(name = "disease_generic_symptoms",
               joinColumns = { @JoinColumn(name = "disease_id") },
               inverseJoinColumns = { @JoinColumn(name = "symptom_id") })
    private List<Symptom> genericSymptoms;

    @ManyToMany
    @JoinTable(name = "disease_specific_symptoms",
               joinColumns = { @JoinColumn(name = "disease_id") },
               inverseJoinColumns = { @JoinColumn(name = "symptom_id") })
    private List<Symptom> specificSymptoms;


    public Boolean hasSymptom(Symptom symptom)
    {
        Set<Symptom> all = new HashSet<>();
        all.addAll(genericSymptoms);
        all.addAll(specificSymptoms);
        return all.contains(symptom);
    }


    public void addGenericSymptom(Symptom symptom)
    {
        if (!this.genericSymptoms.contains(symptom))
            this.genericSymptoms.add(symptom);
    }


    public void addSpecificSymptom(Symptom symptom)
    {
        if (!this.specificSymptoms.contains(symptom))
            this.specificSymptoms.add(symptom);
    }
}
