package com.team1.progettocarsharingteam1.controllers;

import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.enums.ChargeEnum;
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
    public ResponseEntity<Rent> create(@RequestBody Rent rent) {
        Rent rent1 = rentService.create(rent);
        return ResponseEntity.ok().body(rent1);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rent>> findAll(@RequestParam(required = false, defaultValue = "true") boolean isActive) {
        List<Rent> allRentals = rentService.findAll(isActive);
        return ResponseEntity.ok().body(allRentals);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Rent>> findById(@PathVariable Long id) {
        Optional<Rent> rentOPT = rentService.findById(id);
        return ResponseEntity.ok().body(rentOPT);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Rent> edit(@PathVariable Long id, @RequestBody Rent rent) {
        Optional<Rent> updatedRentOPT = rentService.edit(id, rent);
        if (updatedRentOPT.isPresent()) {
            return ResponseEntity.ok().body(updatedRentOPT.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Rent>> delete(@RequestParam Long id) {
        Optional<Rent> deletedRentOPT = rentService.delete(id);
        if (deletedRentOPT.isPresent()) {
            return ResponseEntity.ok().body(deletedRentOPT);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/start/{userId}/{vehicleId}")
    public ResponseEntity<Rent> startRent(@PathVariable Long userId, @PathVariable Long vehicleId) {
        Rent newRent = rentService.startRent(userId, vehicleId);

        if(newRent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(newRent);
    }

    @PutMapping("/end/{userId}/{vehicleId}")
    public ResponseEntity<Rent> endRent(@PathVariable Long userId, @PathVariable Long vehicleId) {
        Optional<Rent> endRentOpt = rentService.endRent(userId, vehicleId);

        if (endRentOpt.isPresent()) {
            return ResponseEntity.ok().body(endRentOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit-active/{id}")
    public ResponseEntity<Rent> editActive(@PathVariable Long id, @RequestParam boolean isActive) {
        Optional<Rent> rentOpt = rentService.editActive(id, isActive);

        if (rentOpt.isPresent()) {
            return ResponseEntity.ok().body(rentOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

}
