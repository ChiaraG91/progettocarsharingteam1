package com.team1.progettocarsharingteam1.repositories;

import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.rentals FROM User u WHERE u.id = :UserId")
    List<Rent> findAllOrdiniByUserId(@Param("UserId") Long UserId);

    List<User> findByName(String name);

    List<User> findBySurname(String surname);
}
