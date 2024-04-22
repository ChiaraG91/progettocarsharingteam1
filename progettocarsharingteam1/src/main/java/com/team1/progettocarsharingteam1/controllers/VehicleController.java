package com.team1.progettocarsharingteam1.controllers;

import com.team1.progettocarsharingteam1.dto.VehicleDTO;
import com.team1.progettocarsharingteam1.entities.enums.CityEnum;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import com.team1.progettocarsharingteam1.services.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Vehicle", description = "API related to the vehicle")
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    @PostMapping("/create")
    public ResponseEntity<VehicleDTO> create(@RequestBody VehicleDTO vehicleDTO) {
        LOGGER.info("creation done");
        return ResponseEntity.ok(vehicleService.create(vehicleDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> findAll(@RequestParam(required = false, defaultValue = "true") boolean isActive) {
        List<VehicleDTO> vehicleList = vehicleService.findAll(isActive);
        if (vehicleList.isEmpty()) {
            LOGGER.info("vehicle not found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(vehicleList);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id) {
        Optional<VehicleDTO> vehicleOpt = vehicleService.findById(id);
        if (vehicleOpt.isPresent()) {
            return ResponseEntity.ok(vehicleOpt.get());
        } else {
            LOGGER.info("vehicle not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<VehicleDTO> edit(@PathVariable Long id, @RequestBody VehicleDTO vehicle) {
        Optional<VehicleDTO> vehicleOpt = vehicleService.edit(id, vehicle);
        if (vehicleOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(vehicleOpt.get());
        } else {
            LOGGER.info("vehicle not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id) {
        Optional<VehicleDTO> vehicleOpt = vehicleService.delete(id);
        if (vehicleOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(vehicleOpt.get());
        } else {
            LOGGER.info("vehicle not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/brand")
    public ResponseEntity<List<VehicleDTO>> findByBrand(@RequestParam String brand) {
        Optional<List<VehicleDTO>> vehicleList = vehicleService.findByBrand(brand);
        if (vehicleList.isEmpty()) {
            LOGGER.info("vehicle not found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(vehicleList.get());
        }
    }

    @GetMapping("/model")
    public ResponseEntity<List<VehicleDTO>> findByModel(@RequestParam String model) {
        Optional<List<VehicleDTO>> vehicleList = vehicleService.findByModel(model);
        if (vehicleList.isEmpty()) {
            LOGGER.info("vehicle not found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(vehicleList.get());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<VehicleDTO>> findByAvailable() {
        Optional<List<VehicleDTO>> vehicleList = vehicleService.findAllAvailable();
        if (vehicleList.isEmpty()) {
            LOGGER.info("vehicle not found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(vehicleList.get());
        }
    }

    @GetMapping("/type")
    public ResponseEntity<List<VehicleDTO>> findByType(@RequestParam TypeVehicleEnum typeVehicleEnum) {
        Optional<List<VehicleDTO>> vehicleList = vehicleService.findByTypeVehicle(typeVehicleEnum);
        if (vehicleList.isEmpty()) {
            LOGGER.info("vehicle not found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(vehicleList.get());
        }
    }

    /**
     * Endpoint for performing a soft delete on a vehicle by setting isActive to false
     *
     * @param id the identifier of the vehicle to be soft deleted
     * @return ResponseEntity containing the soft deleted vehicle, or a 404 Not Found response if the vehicle is not found.
     */
    @Operation(summary = "delete the vehicle")
    @Description("perform a logical delete of a vehicle using the id or return error 404 if the vehicle its not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle found"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<VehicleDTO> softDelete(@PathVariable Long id) {
        Optional<VehicleDTO> vehicleOpt = vehicleService.softDelete(id);
        if (vehicleOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(vehicleOpt.get());
        }
        LOGGER.info("vehicle not found");
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint for restoring a soft deleted vehicle by setting isActive to true
     *
     * @param id the identifier of the vehicle to be restored
     * @return ResponseEntity containing the restored vehicle, or a 404 Not Found response if the vehicle is not found.
     */
    @Operation(summary = "restore deleted vehicle")
    @Description("restore logical delete to get back the vehicle using the id or return error 404 if the vehicle its not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle found"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    @PutMapping("/restore/{id}")
    public ResponseEntity<VehicleDTO> restore(@PathVariable Long id) {
        Optional<VehicleDTO> vehicleOpt = vehicleService.restore(id);
        if (vehicleOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(vehicleOpt.get());
        }
        LOGGER.info("vehicle not found");
        return ResponseEntity.notFound().build();
    }

    /**
     * find a list of vehicles by the specified city.
     *
     * @param city the CityEnum value representing the city to search for vehicles
     * @return ResponseEntity containing a List of VehicleDTO, or a 404 Not Found if no vehicles are found
     */
    @Operation(summary = "find vehicle in the city")
    @Description("find available vehicle in the chosen city or return error 404 if there are no vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicles found"),
            @ApiResponse(responseCode = "404", description = "Vehicles not found")
    })
    @GetMapping("/city")
    public ResponseEntity<List<VehicleDTO>> findAllByCityEnum(@RequestParam CityEnum city) {
        Optional<List<VehicleDTO>> vehicleDTOList = vehicleService.findAllByCityEnum(city);
        if (vehicleDTOList.isEmpty()) {
            LOGGER.info("vehicle not found");
            return ResponseEntity.notFound().build();
        }
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(vehicleDTOList.get());
    }

}
