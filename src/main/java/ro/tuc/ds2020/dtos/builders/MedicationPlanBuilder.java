package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDetailsDTO;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicationPlanBuilder {

    private MedicationPlanBuilder() {
    }

    public static MedicationPlanDTO toMedicationDTO(MedicationPlan medicationPlan) {
        Set<Integer> ids= new HashSet<Integer>();
        if (medicationPlan.getMedication()!=null)
            for(Medication m: medicationPlan.getMedication())
                ids.add(m.getId());
        return new MedicationPlanDTO(medicationPlan.getID(), medicationPlan.getIntervals(), medicationPlan.getTreatment_period(), medicationPlan.getPatient().getID(), ids);
    }

    public static MedicationPlanDetailsDTO toMedicationPlanDetailsDTO(MedicationPlan medicationPlan) {
        Set<Integer> ids= new HashSet<Integer>();
        String names = "";
        String dosage = "";
        if (medicationPlan.getMedication()!=null)
            for(Medication m: medicationPlan.getMedication()) {
                ids.add(m.getId());
                names = names + " -"+ m.getName();
                dosage = dosage + " -"+ m.getDosage();
            }
        return new MedicationPlanDetailsDTO(medicationPlan.getID(), medicationPlan.getIntervals(), medicationPlan.getTreatment_period(), medicationPlan.getPatient().getID(), ids, names, dosage);
    }

    public static MedicationPlan toEntity(MedicationPlanDTO medicationPlan, Patient patient) {
        return new MedicationPlan(medicationPlan.getIntervals(), medicationPlan.getTreatment_period(), patient);
    }
}
