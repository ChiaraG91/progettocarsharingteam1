package com.team1.progettocarsharingteam1.services;

import com.team1.progettocarsharingteam1.entities.User;
import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import com.team1.progettocarsharingteam1.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAll(boolean isActive) {

        if(isActive) {
            List<Vehicle> vehicleList = vehicleRepository.findAllByIsActiveTrue();
            return vehicleList;
        }
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        return vehicleList;
    }

    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findByIdAndIsActiveTrue(id);
    }

    public Optional<Vehicle> edit(Long id, Vehicle vehicle) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findByIdAndIsActiveTrue(id);
        if (vehicleOpt.isPresent()) {
            vehicleOpt.get().setBrand(vehicle.getBrand());
            vehicleOpt.get().setModel(vehicle.getModel());
            vehicleOpt.get().setDetails(vehicle.getDetails());
            vehicleOpt.get().setAvailable(vehicle.isAvailable());
            vehicleOpt.get().setTypeVehicle(vehicle.getTypeVehicle());
            vehicleRepository.save(vehicleOpt.get());
        } else {
            return Optional.empty();
        }
        return vehicleOpt;
    }

    public Optional<Vehicle> delete(Long id) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
        if (vehicleOpt.isPresent()) {
            vehicleRepository.delete(vehicleOpt.get());
            return vehicleOpt;
        } else {
            return Optional.empty();
        }
    }

    /**
     * find a vehicle by brand name
     *
     * @param brand of the vehicle to find
     * @return the list of vehicles with the same brand
     */
    public List<Vehicle> findByBrand(String brand) {
        return vehicleRepository.findByBrandAndIsActiveTrue(brand);
    }

    /**
     * find a vehicle by model name
     *
     * @param model of the vehicle to find
     * @return the list of vehicles with the same model
     */
    public List<Vehicle> findByModel(String model) {
        return vehicleRepository.findByModelAndIsActiveTrue(model);
    }

    /**
     * find a list of vehicles available
     *
     * @return the list of vehicles
     */
    public List<Vehicle> findAllAvailable() {
        return vehicleRepository.findAllAvailable();
    }

    /**
     * find a list of vehicles by type of vehicle enum
     *
     * @param typeVehicleEnum the type of vehicle to find
     * @return the list of vehicles with the same type
     */
    public List<Vehicle> findByTypeVehicle(TypeVehicleEnum typeVehicleEnum) {
        return vehicleRepository.findByTypeVehicleAndIsActiveTrue(typeVehicleEnum);
    }

    /**
     * Sets the isActive field of a vehicle to true or false, effectively performing a soft delete
     *
     * @param id the identifier of the vehicle to be modified.
     * @param isActive the boolean value to set for the isActive field
     * @return an Optional containing the updated vehicle if it exists, or an empty Optional if the vehicle is not found
     */
    public Optional<Vehicle> editActive(Long id, boolean isActive) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);

        if (vehicleOpt.isPresent()){
            vehicleOpt.get().setActive(isActive);

            Vehicle vehicleUpdated = vehicleRepository.save(vehicleOpt.get());
            return Optional.of(vehicleUpdated);
        }
        return Optional.empty();
    }

}
