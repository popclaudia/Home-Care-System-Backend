package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Patient;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MedicationPlanDTO extends RepresentationModel<MedicationPlanDTO> {

    private Integer ID;
    @NotNull
    private String intervals;
    @NotNull
    private String treatment_period;
    @NotNull
    private Integer patientId;
    private Set<Integer> medication;

    public MedicationPlanDTO(Integer ID, @NotNull String intervals, @NotNull String treatment_period, Integer patient, Set <Integer> medication) {
        this.ID = ID;
        this.intervals = intervals;
        this.treatment_period = treatment_period;
        this.patientId = patient;
        this.medication = medication;
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

    public Integer getPatient() {
        return patientId;
    }

    public void setPatient(Integer patient) {
        this.patientId = patient;
    }

    public Set<Integer> getMedication() {
        return medication;
    }

    public void setMedication(Set<Integer> medication) {
        this.medication = medication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationPlanDTO that = (MedicationPlanDTO) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(intervals, that.intervals) &&
                Objects.equals(treatment_period, that.treatment_period) &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(medication, that.medication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, intervals, treatment_period, patientId, medication);
    }
}
