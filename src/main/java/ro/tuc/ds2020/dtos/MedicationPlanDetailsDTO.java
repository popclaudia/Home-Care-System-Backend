package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Patient;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MedicationPlanDetailsDTO extends RepresentationModel<MedicationPlanDetailsDTO> {

    private Integer ID;
    @NotNull
    private String intervals;
    @NotNull
    private String treatment_period;
    @NotNull
    private Integer patientId;

    private Set<Integer> medicationid;

    private  String medicationname;

    private  String medicationdosage;

    public MedicationPlanDetailsDTO(Integer ID, @NotNull String intervals, @NotNull String treatment_period, Integer patient, Set <Integer> medication,  String medicationname,  String medicationdosage) {
        this.ID = ID;
        this.intervals = intervals;
        this.treatment_period = treatment_period;
        this.patientId = patient;
        this.medicationid = medication;
        this.medicationname = medicationname;
        this.medicationdosage = medicationdosage;
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
        return medicationid;
    }

    public void setMedication(Set<Integer> medication) {
        this.medicationid = medication;
    }

    public String getMedicationname() {
        return medicationname;
    }

    public void setMedicationname(String medicationname) {
        this.medicationname = medicationname;
    }

    public String getMedicationdosage() {
        return medicationdosage;
    }

    public void setMedicationdosage(String medicationdosage) {
        this.medicationdosage = medicationdosage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationPlanDetailsDTO that = (MedicationPlanDetailsDTO) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(intervals, that.intervals) &&
                Objects.equals(treatment_period, that.treatment_period) &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(medicationid, that.medicationid)&&
                Objects.equals(medicationname, that.medicationname)&&
                Objects.equals(medicationdosage, that.medicationdosage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, intervals, treatment_period, patientId, medicationid, medicationname, medicationdosage);
    }
}
