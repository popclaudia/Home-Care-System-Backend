package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.entities.Activity;
import ro.tuc.ds2020.services.ActivityService;
import ro.tuc.ds2020.services.CaregiverService;


import javax.validation.Valid;
@RestController
@CrossOrigin
@RequestMapping(value = "/activity")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping()
    public ResponseEntity<Integer> insertActivity(@Valid @RequestBody Activity activity) {
        Integer ActivityID = activityService.insert(activity);
        System.out.println(ActivityID);
        return new ResponseEntity<>(ActivityID, HttpStatus.CREATED);

    }
}
