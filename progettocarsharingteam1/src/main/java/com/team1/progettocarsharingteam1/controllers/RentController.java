package com.team1.progettocarsharingteam1.controllers;

import com.team1.progettocarsharingteam1.dto.RentDTO;
import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.services.RentService;
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

    @PostMapping("/create")
    public ResponseEntity<RentDTO> create(@RequestBody RentDTO rent) {
        return ResponseEntity.ok(rentService.create(rent));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RentDTO>> findAll(@RequestParam(required = false, defaultValue = "true") boolean isActive) {
        List<RentDTO> allRentals = rentService.findAll(isActive);
        return ResponseEntity.ok().body(allRentals);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<RentDTO>> findById(@PathVariable Long id) {
        Optional<RentDTO> rentOPT = rentService.findById(id);
        return ResponseEntity.ok().body(rentOPT);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<RentDTO> edit(@PathVariable Long id, @RequestBody Rent rent) {
        Optional<RentDTO> updatedRentOPT = rentService.edit(id, rent);
        if (updatedRentOPT.isPresent()) {
            return ResponseEntity.ok().body(updatedRentOPT.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<RentDTO>> delete(@RequestParam Long id) {
        Optional<RentDTO> deletedRentOPT = rentService.delete(id);
        if (deletedRentOPT.isPresent()) {
            return ResponseEntity.ok().body(deletedRentOPT);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/user/{id}")
    ResponseEntity<List<RentDTO>> rentById(@PathVariable Long id) {
        Optional<List<RentDTO>> optionalRents = rentService.rentByid(id);
        if (optionalRents.isPresent()) {
            return ResponseEntity.ok(optionalRents.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<RentDTO> endRent(@PathVariable Long id) {
        Optional<RentDTO> endRentOpt = rentService.endRent(id);
        if (endRentOpt.isPresent()) {
            return ResponseEntity.ok().body(endRentOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint for updating the isActive field of a rent
     *
     * @param id the identifier of the rent to be updated
     * @param isActive the boolean value to set for the isActive field
     * @return ResponseEntity containing the updated rent, or a 404 Not Found response if the rent is not found.
     */
    @PutMapping("/edit-active/{id}")
    public ResponseEntity<RentDTO> editActive(@PathVariable Long id, @RequestParam boolean isActive) {
        Optional<RentDTO> rentOpt = rentService.editActive(id, isActive);
        if (rentOpt.isPresent()) {
            return ResponseEntity.ok().body(rentOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

}
