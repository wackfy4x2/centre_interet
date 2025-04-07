package com.tutore.centre_interet.Services;

import com.tutore.centre_interet.DTO.ReservationDTO;
import com.tutore.centre_interet.Entities.Reservation;

import java.util.List;

public interface ReservationService {
    public ReservationDTO save(Reservation reservation);
    public List<ReservationDTO> findall(Long iduser);
}
