package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.CareGiver;
import ro.tuc.ds2020.entities.Patient;

public class PatientBuilder {

    private PatientBuilder() {
    }

    public static PatientDTO toPatientDTO(Patient patient) {
        Integer caregiver=null;
        if(patient.getCaregiver()!=null)
            caregiver=patient.getCaregiver().getID();
        return new PatientDTO(patient.getID(), patient.getName(), patient.getBirth_date(), patient.getGender(), patient.getAddress(), patient.getMedical_record(), caregiver);
    }

    public static Patient toEntity(PatientDTO patient, CareGiver caregiver) {
        return new Patient(patient.getName(), patient.getBirthdate(), patient.getGender(), patient.getAddress(), patient.getMedical_record(), caregiver);
    }

}
