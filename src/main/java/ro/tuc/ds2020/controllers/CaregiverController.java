package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.services.CaregiverService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/caregiver")
public class CaregiverController {


    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @GetMapping()
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        for (CaregiverDTO dto : dtos) {
            Link CaregiverLink = linkTo(methodOn(CaregiverController.class)
                    .getCaregiver(dto.getId())).withRel("CaregiverDetails");
            dto.add(CaregiverLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertCaregiver(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        Integer CaregiverID = caregiverService.insert(caregiverDTO);
        return new ResponseEntity<>(CaregiverID, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deleteCaregiver(@PathVariable("id") Integer caregiverId) {
        caregiverService.delete(caregiverId);
        return new ResponseEntity<>(caregiverId, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") Integer caregiverId) {
        CaregiverDTO dto = caregiverService.findCaregiverById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> updateCaregiver(@RequestBody CaregiverDTO caregiver, @PathVariable("id") Integer CaregiverId) {
        CaregiverDTO dto = caregiverService.update(caregiver, CaregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }


}
