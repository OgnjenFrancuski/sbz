package rs.ac.uns.ftn.sbz.backend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.enumeration.DiseaseGroup;
import rs.ac.uns.ftn.sbz.backend.model.enumeration.MedicationType;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diagnosis")
@Getter
@Setter
@AllArgsConstructor
public class Diagnosis
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diagnosis_id")
    private Long id;

    @ManyToOne
    @JoinTable(name = "diagnosis_physician",
               joinColumns = { @JoinColumn(name = "diagnosis_id") },
               inverseJoinColumns = { @JoinColumn(name = "person_id") })
    private Physician physician;

    @ManyToOne
    @JoinTable(name = "diagnosis_patient",
               joinColumns = { @JoinColumn(name = "diagnosis_id") },
               inverseJoinColumns = { @JoinColumn(name = "person_id") })
    private Patient patient;

    @ManyToMany()
    @JoinTable(name = "diagnosis_symptoms",
               joinColumns = { @JoinColumn(name = "diagnosis_id") },
               inverseJoinColumns = { @JoinColumn(name = "symptom_id") })
    private Set<Symptom> symptoms;

    @ManyToMany()
    @JoinTable(name = "diagnosis_diseases",
               joinColumns = { @JoinColumn(name = "diagnosis_id") },
               inverseJoinColumns = { @JoinColumn(name = "disease_id") })
    private Set<Disease> diseases;

    @ManyToMany()
    @JoinTable(name = "diagnosis_medications",
            joinColumns = { @JoinColumn(name = "diagnosis_id") },
            inverseJoinColumns = { @JoinColumn(name = "medication_id") })
    private Set<Medication> prescribedMedications;

    @Column(name = "diagnosis_body_temperature")
    private Double bodyTemperature;

    @Column(name = "diagnosis_date")
    private Date date;


    public Diagnosis()
    {
        this.diseases = new HashSet<>();
        this.prescribedMedications = new HashSet<>();
    }


    public void addDisease(Disease disease)
    {
        this.diseases.add(disease);
    }


    public Boolean hasDisease(String name)
    {
        for (Disease d : this.diseases)
            if (d.getName().equalsIgnoreCase(name))
                return true;
        return false;
    }


    public Boolean hasDiseaseFromGroup(DiseaseGroup group)
    {
        for (Disease d: this.diseases)
            if (d.getGroup() == group)
                return true;
        return false;
    }


    public Boolean hasSymptom(String name)
    {
        for (Symptom s : this.symptoms)
            if (s.getName().equalsIgnoreCase(name))
                return true;
        return false;
    }


    public Boolean hasSymptom(Symptom symptom)
    {
        return this.symptoms.contains(symptom);
    }


    public Boolean hasPrescribedMedicationType(MedicationType type)
    {
        if (this.prescribedMedications == null)
            return false;

        for (Medication m : this.prescribedMedications)
            if (m.getType() == type)
                return true;
        return false;
    }

}
