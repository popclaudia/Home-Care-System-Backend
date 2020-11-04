package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDetailsDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.MedicationBuilder;
import ro.tuc.ds2020.dtos.builders.MedicationPlanBuilder;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);
    private final MedicationPlanRepository medicationPlanRepository;
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository, PatientRepository patientRepository, MedicationRepository medicationRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.patientRepository = patientRepository;
        this.medicationRepository = medicationRepository;
    }

    public Integer insert(MedicationPlanDTO medicationPlanDTO) {
        Optional<Patient> prosumerOptional = patientRepository.findById(medicationPlanDTO.getPatient());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", medicationPlanDTO.getPatient());
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + medicationPlanDTO.getPatient());
        }
        Set<Medication> medication=new HashSet<Medication>();
        if(medicationPlanDTO.getMedication()!=null)
        for(Integer i: medicationPlanDTO.getMedication()) {
            Optional<Medication> medicationOptional = medicationRepository.findById(i);
            medication.add(medicationOptional.get());
        }
        Patient patient = prosumerOptional.get();
        MedicationPlan medicationPlan = MedicationPlanBuilder.toEntity(medicationPlanDTO, patient);
        medicationPlan.setMedication(medication);
        medicationPlan = medicationPlanRepository.save(medicationPlan);
        LOGGER.debug("MedicationPlan with id {} was inserted in db", medicationPlan.getID());
        return medicationPlan.getID();
    }

    public List<MedicationPlanDetailsDTO> findMedicationPlans(Integer id) {
        List<MedicationPlan> medicationPlansList = medicationPlanRepository.findByPatient(id);
        return medicationPlansList.stream()
                .map(MedicationPlanBuilder::toMedicationPlanDetailsDTO)
                .collect(Collectors.toList());
    }

    public MedicationPlanDetailsDTO findPlanById(Integer id) {
        Optional<MedicationPlan> prosumerOptional = medicationPlanRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication plan with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + id);
        }
        return MedicationPlanBuilder.toMedicationPlanDetailsDTO(prosumerOptional.get());
    }
}
