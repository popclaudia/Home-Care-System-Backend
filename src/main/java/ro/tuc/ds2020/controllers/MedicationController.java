package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.services.MedicationService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }



    @GetMapping()
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();
        for (MedicationDTO dto : dtos) {
            Link medicationLink = linkTo(methodOn(MedicationController.class)
                    .getMedication(dto.getId())).withRel("medicationDetails");
            dto.add(medicationLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertMedication(@Valid @RequestBody MedicationDTO medicationDTO) {
        Integer medicationID = medicationService.insert(medicationDTO);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deleteMedication(@PathVariable("id") Integer medicationId) {
        medicationService.delete(medicationId);
        return new ResponseEntity<>(medicationId, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") Integer medicationId) {
        MedicationDTO dto = medicationService.findMedicationById(medicationId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> updateMedication(@RequestBody MedicationDTO medication, @PathVariable("id") Integer medicationId) {
        MedicationDTO dto = medicationService.update(medication, medicationId);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
    
}
