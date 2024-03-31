package com.team1.progettocarsharingteam1.repositories;

import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByBrand(String brand);

    List<Vehicle> findByModel(String model);

    @Query(value = "SELECT * FROM Vehicle WHERE is_available = true", nativeQuery = true)
    List<Vehicle> findAllAvailable();

    List<Vehicle> findByTypeVehicle(TypeVehicleEnum typeVehicleEnum);

}
