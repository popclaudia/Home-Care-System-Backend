package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class MedicationPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer ID;

    @Column(name = "intervals", nullable = false)
    private String intervals;

    @Column(name = "treatment_period", nullable = false)
    private String treatment_period;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "ID")
    private Patient patient;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "Medication_MadicationPlans",
            joinColumns = @JoinColumn(name = "Plan_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "Medication_ID", referencedColumnName = "Id")
    )

    private Set<Medication> medication=new HashSet<Medication>();

    public MedicationPlan(){

    }

    public MedicationPlan(String intervals, String treatment_period, Patient patient) {
        this.intervals = intervals;
        this.treatment_period = treatment_period;
        this.patient = patient;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public String getTreatment_period() {
        return treatment_period;
    }

    public void setTreatment_period(String treatment_period) {
        this.treatment_period = treatment_period;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Medication> getMedication() {
        return medication;
    }

    public void setMedication(Set<Medication> medication) {
        this.medication = medication;
    }
}
