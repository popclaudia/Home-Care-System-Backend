package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.dtos.MedicationPlanDetailsDTO;
import ro.tuc.ds2020.services.MedicationPlanService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/medicationplan")
public class MedicationPlanController {

    private final MedicationPlanService medicationPlanService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService) {
        this.medicationPlanService = medicationPlanService;
    }

    @GetMapping(value = "patient/{id}")
    public ResponseEntity<List<MedicationPlanDetailsDTO>> getMedicationPlansForPatient(@PathVariable("id") Integer id) {
        List<MedicationPlanDetailsDTO> dtos = medicationPlanService.findMedicationPlans(id);
        for (MedicationPlanDetailsDTO dto : dtos) {
            Link mLink = linkTo(methodOn(MedicationPlanController.class)
                    .getMedicationPlan(dto.getID())).withRel("mpDetails");
            dto.add(mLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<Integer> insertMedicationPlan(@Valid @RequestBody MedicationPlanDTO medicationPlanDTO) {
        Integer medicationPlanID = medicationPlanService.insert(medicationPlanDTO);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationPlanDetailsDTO> getMedicationPlan(@PathVariable("id") Integer id) {
        MedicationPlanDetailsDTO dto = medicationPlanService.findPlanById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
