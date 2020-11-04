package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.entities.CareGiver;
import ro.tuc.ds2020.entities.Patient;

import java.util.ArrayList;
import java.util.List;

public class CaregiverBuilder {

    private CaregiverBuilder() {
    }

    public static CaregiverDTO toCaregiverDTO(CareGiver caregiver) {
        List<Integer> ids= new ArrayList<Integer>();
        if (caregiver.getPatients()!=null)
            for(Patient p: caregiver.getPatients())
                ids.add(p.getID());
        return new CaregiverDTO(caregiver.getID(), caregiver.getName(), caregiver.getBirth_date(), caregiver.getGender(), caregiver.getAddress(), ids);

    }

    public static CareGiver toEntity(CaregiverDTO caregiver) {
        return new CareGiver(caregiver.getName(), caregiver.getBirthdate(), caregiver.getGender(), caregiver.getAddress());
    }


}
