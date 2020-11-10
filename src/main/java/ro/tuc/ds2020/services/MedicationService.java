package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.builders.MedicationBuilder;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);
    private final MedicationRepository medicationRepository;
    private final MedicationPlanRepository medicationPlanRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository, MedicationPlanRepository medicationPlanRepository) {
        this.medicationRepository = medicationRepository;
        this.medicationPlanRepository = medicationPlanRepository;
    }

    public List<MedicationDTO> findMedications() {
        List<Medication> medicationList = medicationRepository.findAll();
        return medicationList.stream()
                .map(MedicationBuilder::toMedicationDTO)
                .collect(Collectors.toList());
    }

    public MedicationDTO findMedicationById(Integer id) {
        Optional<Medication> prosumerOptional = medicationRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());
    }

    public void delete(Integer id) {
        Optional<Medication> medicationOptional = medicationRepository.findMedicationById(id);
        if (!medicationOptional.isPresent()) {
            LOGGER.error("Medication with id {} doesn't exist in DB", id);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        Medication m = medicationOptional.get();
        if(m.getMedication_plans()!=null)
            for(MedicationPlan p: m.getMedication_plans()){
                p.getMedication().remove(m);
                medicationPlanRepository.save(p);
            }
        medicationRepository.deleteById(id);
        LOGGER.debug("Medication with id {} was deleted from the db", id);
    }

    public MedicationDTO update(MedicationDTO medicationDTO, Integer medicationId){

        Optional<Medication> medicationOptional = medicationRepository.findById(medicationId);
        if (!medicationOptional.isPresent()) {
            LOGGER.error("Medication with id {} doesn't exist in DB", medicationId);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + medicationId);
        }
        Medication medication = MedicationBuilder.toEntity(medicationDTO);
        medication.setId(medicationId);
        medicationDTO.setId(medicationId);
        medicationRepository.save(medication);
        return medicationDTO;
    }

    public Integer insert(MedicationDTO medicationDTO) {
        Medication medication = MedicationBuilder.toEntity(medicationDTO);
        medication = medicationRepository.save(medication);
        LOGGER.debug("Medication with id {} was inserted in db", medication.getId());
        return medication.getId();
    }



}
