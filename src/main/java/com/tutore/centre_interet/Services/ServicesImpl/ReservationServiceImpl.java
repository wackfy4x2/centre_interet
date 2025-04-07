package com.tutore.centre_interet.Services.ServicesImpl;

import com.tutore.centre_interet.DTO.ReservationDTO;
import com.tutore.centre_interet.Entities.Reservation;
import com.tutore.centre_interet.Repository.ReservationRepository;
import com.tutore.centre_interet.Services.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public static ReservationDTO toDTO(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setDatereservation(reservation.getDatereservation());
        dto.setIduser(reservation.getIduser());
        dto.setDateprevu(reservation.getDateprevu());
        dto.setService(reservation.getServiceent().getNom());
//        dto.setCentreInterets(categorie.getCentreInterets());
        return dto;
    }

    @Override
    public ReservationDTO save(Reservation reservation) {
        return toDTO(reservationRepository.save(reservation));
    }

    @Override
    public List<ReservationDTO> findall(Long iduser) {
        List<Reservation> reservation = reservationRepository.findReservationByIduser(iduser);
        List<ReservationDTO> serviceDTOS = reservation.stream()
                .map(ReservationServiceImpl::toDTO)
                .collect(Collectors.toList());
        return serviceDTOS;
    }
}
