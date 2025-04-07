package com.tutore.centre_interet.Controller;


import com.tutore.centre_interet.DTO.ServiceDTO;
import com.tutore.centre_interet.Entities.Serviceent;
import com.tutore.centre_interet.Services.ServicesImpl.ServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("service")
public class ServiceController {
    private ServiceImpl service;

    public ServiceController(ServiceImpl service) {
        this.service = service;
    }

    @PostMapping("")
    private Map<String, Object> save(@RequestBody Serviceent serviceent) {
        ServiceDTO serviceDTO = service.save(serviceent);
        Map<String, Object> response = new HashMap<>();
        if (serviceDTO == null) {
            response.put("message", "echec de l'enregistrement");
        } else {
            response.put("message", "success");
        }
        return response;
    }

    @PutMapping("")
    private Map<String, Object> update(@RequestBody Serviceent serviceent) {
        ServiceDTO serviceDTO = service.update(serviceent);
        Map<String, Object> response = new HashMap<>();
        if (serviceDTO == null) {
            response.put("message", "echec de la mise a jour");
        } else {
            response.put("message", "success");
        }
        return response;
    }

    @PutMapping("del")
    private Map<String, Object> delete(@RequestBody Long id) {
        ServiceDTO serviceDTO = service.delete(id);
        Map<String, Object> response = new HashMap<>();
        if (serviceDTO == null) {
            response.put("message", "echec de la suppression");
        } else {
            response.put("message", "success");
        }
        return response;
    }

    @GetMapping("findbyid")
    private Map<String, Object> findbyid(@RequestBody Long id) {
        ServiceDTO serviceDTO = service.findbyid(id);
        Map<String, Object> response = new HashMap<>();
        if (serviceDTO == null) {
            response.put("message", "Service Introuvable");
        } else {
            response.put("message", "success");
            response.put("Service", serviceDTO);
        }
        return response;
    }


}
