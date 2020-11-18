package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.builders.MedicationBuilder;
import ro.tuc.ds2020.entities.Activity;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.ActivityRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.Optional;


@Service
public class ActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final ActivityRepository activityRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, PatientRepository patientRepository ) {

        this.activityRepository = activityRepository;
        this.patientRepository = patientRepository;
    }

    public Integer insert(Activity activity) {
        activity = activityRepository.save(activity);
        Optional<Patient> prosumerOptional = patientRepository.findByIdd(activity.getPatientId());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", activity.getPatientId());
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + activity.getPatientId());
        }
        LOGGER.debug("Activity with id {} was inserted in db", activity.getId());
        return prosumerOptional.get().getCaregiver().getID();
    }


}
