package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Patient;

public class MedicationBuilder {

    private MedicationBuilder() {
    }

    public static MedicationDTO toMedicationDTO(Medication medication) {
        return new MedicationDTO(medication.getId(), medication.getName(), medication.getSide_effects(), medication.getDosage());
    }

    public static Medication toEntity(MedicationDTO medication) {
        return new Medication(medication.getName(), medication.getSide_effects(), medication.getDosage());
    }
}
