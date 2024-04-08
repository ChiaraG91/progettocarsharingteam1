package com.team1.progettocarsharingteam1.controllers;

import com.team1.progettocarsharingteam1.dto.VehicleDTO;
import com.team1.progettocarsharingteam1.entities.User;
import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import com.team1.progettocarsharingteam1.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/create")
    public ResponseEntity<VehicleDTO> create(@RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.create(vehicleDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> findAll(@RequestParam(required = false, defaultValue = "true") boolean isActive) {
        List<VehicleDTO> vehicleList = vehicleService.findAll(isActive);
        if (vehicleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicleList);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id) {
        Optional<VehicleDTO> vehicleOpt = vehicleService.findById(id);
        if (vehicleOpt.isPresent()) {
            return ResponseEntity.ok(vehicleOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<VehicleDTO> edit(@PathVariable Long id, @RequestBody VehicleDTO vehicle) {
        Optional<VehicleDTO> vehicleOpt = vehicleService.edit(id, vehicle);
        if (vehicleOpt.isPresent()) {
            return ResponseEntity.ok(vehicleOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Vehicle> delete(@PathVariable Long id) {
        Optional<Vehicle> vehicleOpt = vehicleService.delete(id);
        if (vehicleOpt.isPresent()) {
            return ResponseEntity.ok(vehicleOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * find a vehicle by brand name
     *
     * @param brand of the vehicle to find
     * @return the list of vehicles with the same brand
     */
    @GetMapping("/brand")
    public ResponseEntity<List<Vehicle>> findByBrand(@RequestParam String brand) {
        List<Vehicle> vehicleList = vehicleService.findByBrand(brand);
        if (vehicleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicleList);
        }
    }

    /**
     * find a vehicle by model name
     *
     * @param model of the vehicle to find
     * @return the list of vehicles with the same model
     */
    @GetMapping("/model")
    public ResponseEntity<List<Vehicle>> findByModel(@RequestParam String model) {
        List<Vehicle> vehicleList = vehicleService.findByModel(model);
        if (vehicleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicleList);
        }
    }

    /**
     * find a list of only available vehicles
     *
     * @return the list of available vehicles
     */
    @GetMapping("/available")
    public ResponseEntity<List<Vehicle>> findByAvailable() {
        List<Vehicle> vehicleList = vehicleService.findAllAvailable();
        if (vehicleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicleList);
        }
    }

    /**
     * find a list of vehicles by type of vehicle enum
     *
     * @param typeVehicleEnum the type of vehicle to find
     * @return the list of vehicles with the same type
     */
    @GetMapping("/type")
    public ResponseEntity<List<Vehicle>> findByType(@RequestParam TypeVehicleEnum typeVehicleEnum) {
        List<Vehicle> vehicleList = vehicleService.findByTypeVehicle(typeVehicleEnum);
        if (vehicleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicleList);
        }
    }

    /**
     * Endpoint for updating the isActive field of a vehicle
     *
     * @param id the identifier of the vehicle to be updated
     * @param isActive the boolean value to set for the isActive field
     * @return ResponseEntity containing the updated vehicle, or a 404 Not Found response if the vehicle is not found.
     */
    @PutMapping("/edit-active/{id}")
    public ResponseEntity<Vehicle> editActive(@PathVariable Long id, @RequestParam boolean isActive) {
        Optional<Vehicle> vehicleOpt = vehicleService.editActive(id, isActive);

        if (vehicleOpt.isPresent()) {
            return ResponseEntity.ok().body(vehicleOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

}
