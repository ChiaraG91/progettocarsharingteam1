package com.team1.progettocarsharingteam1.services;

import com.team1.progettocarsharingteam1.dto.VehicleDTO;
import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.entities.enums.CityEnum;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import com.team1.progettocarsharingteam1.repositories.VehicleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public VehicleDTO create(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO ,vehicle);
        vehicle.setAvailable(true);
        Vehicle vehicle1 = vehicleRepository.save(vehicle);
        BeanUtils.copyProperties(vehicle1, vehicleDTO);
        return vehicleDTO;
    }

    public List<VehicleDTO> findAll(boolean isActive) {
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        VehicleDTO vehicleDTO = new VehicleDTO();
        if(isActive) {
            List<Vehicle> vehicleList = vehicleRepository.findAllByIsActiveTrue();
            for (Vehicle vehicle : vehicleList) {
                BeanUtils.copyProperties(vehicle, vehicleDTO);
                vehicleDTOList.add(vehicleDTO);
            }
            return vehicleDTOList;
        }
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        for (Vehicle vehicle : vehicleList) {
            BeanUtils.copyProperties(vehicle, vehicleDTO);
            vehicleDTOList.add(vehicleDTO);
        }
        return vehicleDTOList;
    }

    public Optional<VehicleDTO> findById(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            VehicleDTO vehicleDTO = new VehicleDTO();
            BeanUtils.copyProperties(vehicleOptional.get(), vehicleDTO);
            return Optional.of(vehicleDTO);
        } else {
            return Optional.empty();
        }
    }

    public Optional<VehicleDTO> edit(Long id, VehicleDTO vehicle) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findByIdAndIsActiveTrue(id);
        if (vehicleOpt.isPresent()) {
            if (vehicle.getBrand() != null) {
                vehicleOpt.get().setBrand(vehicle.getBrand());
            }
            if (vehicle.getModel() != null) {
                vehicleOpt.get().setModel(vehicle.getModel());
            }
            if (vehicle.getDetails() != null) {
                vehicleOpt.get().setDetails(vehicle.getDetails());
            }
            vehicleOpt.get().setAvailable(vehicle.isAvailable());
            if (vehicle.getTypeVehicle() != null) {
                vehicleOpt.get().setTypeVehicle(vehicle.getTypeVehicle());
            }
            Vehicle vehicle1 = vehicleRepository.save(vehicleOpt.get());
            BeanUtils.copyProperties(vehicle1, vehicle);
            return Optional.of(vehicle);
        } else {
            return Optional.empty();
        }
    }

    public Optional<VehicleDTO> delete(Long id) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
        if (vehicleOpt.isPresent()) {
            VehicleDTO vehicleDTO = new VehicleDTO();
            vehicleOpt.get().setActive(false);
            Vehicle vehicle = vehicleRepository.save(vehicleOpt.get());
            BeanUtils.copyProperties(vehicle, vehicleDTO);
            return Optional.of(vehicleDTO);
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
    public Optional<List<VehicleDTO>> findByBrand(String brand) {
        List<Vehicle> vehicleList = vehicleRepository.findByBrandAndIsActiveTrue(brand);
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        if (vehicleList.isEmpty()) {
            return Optional.empty();
        } else {
            for (Vehicle vehicle : vehicleList) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                BeanUtils.copyProperties(vehicle, vehicleDTO);
                vehicleDTOList.add(vehicleDTO);
            }
            return Optional.of(vehicleDTOList);
        }
    }

    /**
     * find a vehicle by model name
     *
     * @param model of the vehicle to find
     * @return the list of vehicles with the same model
     */
    public Optional<List<VehicleDTO>> findByModel(String model) {
        List<Vehicle> vehicleList = vehicleRepository.findByModelAndIsActiveTrue(model);
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        if (vehicleList.isEmpty()) {
            return Optional.empty();
        } else {
            for (Vehicle vehicle : vehicleList) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                BeanUtils.copyProperties(vehicle, vehicleDTO);
                vehicleDTOList.add(vehicleDTO);
            }
            return Optional.of(vehicleDTOList);
        }
    }

    /**
     * find a list of vehicles available
     *
     * @return the list of vehicles
     */
    public Optional<List<VehicleDTO>> findAllAvailable() {
        List<Vehicle> vehicleList = vehicleRepository.findAllAvailable();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        if (vehicleList.isEmpty()) {
            return Optional.empty();
        } else {
            for (Vehicle vehicle : vehicleList) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                BeanUtils.copyProperties(vehicle, vehicleDTO);
                vehicleDTOList.add(vehicleDTO);
            }
            return Optional.of(vehicleDTOList);
        }
    }

    /**
     * find a list of vehicles by type of vehicle enum
     *
     * @param typeVehicleEnum the type of vehicle to find
     * @return the list of vehicles with the same type
     */
    public Optional<List<VehicleDTO>> findByTypeVehicle(TypeVehicleEnum typeVehicleEnum) {
        List<Vehicle> vehicleList = vehicleRepository.findByTypeVehicleAndIsActiveTrue(typeVehicleEnum);
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        if (vehicleList.isEmpty()) {
            return Optional.empty();
        } else {
            for (Vehicle vehicle : vehicleList) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                BeanUtils.copyProperties(vehicle, vehicleDTO);
                vehicleDTOList.add(vehicleDTO);
            }
            return Optional.of(vehicleDTOList);
        }
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

    /**
     * find a list of VehicleDTO based on the specified CityEnum
     *
     * @param city the CityEnum value representing the city to search for vehicles
     * @return a List of VehicleDTO found in the specified city
     */
    public Optional<List<VehicleDTO>> findAllByCityEnum(CityEnum city) {
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        List<Vehicle> vehicleList = vehicleRepository.findAllByCityEnumAndIsActiveTrue(city);
        if (vehicleList.isEmpty()) {
            return Optional.empty();
        } else {
            for (Vehicle vehicle : vehicleList) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                BeanUtils.copyProperties(vehicle, vehicleDTO);
                vehicleDTOList.add(vehicleDTO);
            }
            return Optional.of(vehicleDTOList);
        }

    }
}
