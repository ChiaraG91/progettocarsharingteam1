package com.team1.progettocarsharingteam1.repositories;

import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.entities.enums.CityEnum;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByBrandAndIsActiveTrue(String brand);

    List<Vehicle> findByModelAndIsActiveTrue(String model);

    @Query(value = "SELECT * FROM Vehicle WHERE is_available = true and is_active = true", nativeQuery = true)
    List<Vehicle> findAllAvailable();

    List<Vehicle> findByTypeVehicleAndIsActiveTrue(TypeVehicleEnum typeVehicleEnum);

    Optional<Vehicle> findByIdAndIsActiveTrue(Long id);

    List<Vehicle> findAllByIsActiveTrue();

    List<Vehicle> findAllByCityEnumAndIsActiveTrue(CityEnum city);
}
