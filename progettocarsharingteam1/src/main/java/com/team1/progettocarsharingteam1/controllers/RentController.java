package com.team1.progettocarsharingteam1.controllers;

import com.team1.progettocarsharingteam1.dto.RentCleanDTO;
import com.team1.progettocarsharingteam1.dto.RentDTO;
import com.team1.progettocarsharingteam1.services.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RentController.class);

    @PostMapping("/create")
    public ResponseEntity<RentCleanDTO> create(@RequestBody RentDTO rent) {
        LOGGER.info("creation done");
        return ResponseEntity.ok(rentService.create(rent));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RentCleanDTO>> findAll(@RequestParam(required = false, defaultValue = "true") boolean isActive) {
        List<RentCleanDTO> allRentals = rentService.findAll(isActive);
        LOGGER.info("operation completed");
        return ResponseEntity.ok().body(allRentals);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<RentCleanDTO>> findById(@PathVariable Long id) {
        Optional<RentCleanDTO> rentOPT = rentService.findById(id);
        LOGGER.info("operation completed");
        return ResponseEntity.ok().body(rentOPT);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<RentCleanDTO> edit(@PathVariable Long id, @RequestBody RentDTO rent) {
        Optional<RentCleanDTO> updatedRentOPT = rentService.edit(id, rent);
        if (updatedRentOPT.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(updatedRentOPT.get());
        }
            LOGGER.info("rent not found");
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<RentCleanDTO>> delete(@RequestParam Long id) {
        Optional<RentCleanDTO> deletedRentOPT = rentService.delete(id);
        if (deletedRentOPT.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(deletedRentOPT);
        }
            LOGGER.info("rent not found");
            return ResponseEntity.notFound().build();

    }

    /**
     * endpoint to get all the rents of a user
     *
     * @param id the id of the user
     * @return a list of all the rents of the user or error 404 if the user is not found
     */
    @GetMapping("/user/{id}")
    ResponseEntity<List<RentCleanDTO>> rentById(@PathVariable Long id) {
        Optional<List<RentCleanDTO>> optionalRents = rentService.rentByid(id);
        if (optionalRents.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(optionalRents.get());
        } else {
            LOGGER.info("rent not found");
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * endpoint to end a rent of a user
     *
     * @param id the id of the rent to end
     * @return the updated rent or error 404 if the rent is not found
     */
    @PutMapping("/end/{id}")
    public ResponseEntity<RentCleanDTO> endRent(@PathVariable Long id) {
        Optional<RentCleanDTO> endRentOpt = rentService.endRent(id);
        if (endRentOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(endRentOpt.get());
        }
            LOGGER.info("rent not found");
            return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint for updating the isActive field of a rent
     *
     * @param id       the identifier of the rent to be updated
     * @param isActive the boolean value to set for the isActive field
     * @return ResponseEntity containing the updated rent, or a 404 Not Found response if the rent is not found.
     */
    @PutMapping("/edit-active/{id}")
    public ResponseEntity<RentCleanDTO> editActive(@PathVariable Long id, @RequestParam boolean isActive) {
        Optional<RentCleanDTO> rentOpt = rentService.editActive(id, isActive);
        if (rentOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(rentOpt.get());
        }
            LOGGER.info("rent not found");
            return ResponseEntity.notFound().build();
    }

}
