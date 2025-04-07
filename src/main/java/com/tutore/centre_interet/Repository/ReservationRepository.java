package com.tutore.centre_interet.Repository;

import com.tutore.centre_interet.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findReservationByIduser(Long iduser);
}
