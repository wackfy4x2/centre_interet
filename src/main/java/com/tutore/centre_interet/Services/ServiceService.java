package com.tutore.centre_interet.Services;

import com.tutore.centre_interet.DTO.ServiceDTO;
import com.tutore.centre_interet.Entities.Serviceent;

import java.util.List;

public interface ServiceService {
    public ServiceDTO save(Serviceent serviceent);
    public ServiceDTO delete(Long id);
    public ServiceDTO update(Serviceent serviceent);
    public ServiceDTO findbyid(Long id);
    public List<ServiceDTO> findall();
}
