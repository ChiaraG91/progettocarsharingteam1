package com.team1.progettocarsharingteam1.repositories;

import com.team1.progettocarsharingteam1.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {

    Optional<Rent> findByIdAndIsActiveTrue(Long id);

    List<Rent> findAllByIsActiveTrue();
}
