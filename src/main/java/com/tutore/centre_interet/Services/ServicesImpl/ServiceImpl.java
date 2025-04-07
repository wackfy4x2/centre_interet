package com.tutore.centre_interet.Services.ServicesImpl;

import com.tutore.centre_interet.DTO.ServiceDTO;
import com.tutore.centre_interet.Entities.Serviceent;
import com.tutore.centre_interet.Repository.ServiceRepo;
import com.tutore.centre_interet.Services.ServiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceImpl implements ServiceService {
    private ServiceRepo serviceRepo;

    public ServiceImpl(ServiceRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    public static ServiceDTO toDTO(Serviceent serviceent) {
        if (serviceent == null) {
            return null;
        }
        ServiceDTO dto = new ServiceDTO();
        dto.setId(serviceent.getId());
        dto.setNom(serviceent.getNom());
        dto.setDescription(serviceent.getDescription());
        dto.setManifestations(serviceent.getManifestations());
        return dto;
    }

    @Override
    public ServiceDTO save(Serviceent serviceent) {
        serviceent.setStatus(true);
        return toDTO(serviceRepo.save(serviceent));
    }

    @Override
    public ServiceDTO delete(Long id) {
        Serviceent serviceent = serviceRepo.findServiceById(id);
        serviceent.setStatus(false);
        return toDTO(serviceRepo.save(serviceent));
    }

    @Override
    public ServiceDTO update(Serviceent serviceent) {
        return toDTO(serviceRepo.save(serviceent));
    }

    @Override
    public ServiceDTO findbyid(Long id) {
        return toDTO(serviceRepo.findServiceById(id));
    }

    @Override
    public List<ServiceDTO> findall() {
        List<Serviceent> services = serviceRepo.findAll();
        List<ServiceDTO> serviceDTOS = services.stream()
                .map(ServiceImpl::toDTO)
                .collect(Collectors.toList());
        return serviceDTOS;
    }
}
