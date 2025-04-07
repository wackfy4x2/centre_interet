package com.tutore.centre_interet.Controller;

import com.tutore.centre_interet.DTO.ContactDTO;
import com.tutore.centre_interet.Entities.Contact;
import com.tutore.centre_interet.Services.ServicesImpl.ContactServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("contact")
public class ContactController {
    private ContactServiceImpl contactService;

    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @PostMapping("")
    private Map<String, Object> save(@RequestBody Contact contact) {
        ContactDTO contactDTO = contactService.save(contact);
        Map<String, Object> response = new HashMap<>();
        if (contactDTO == null) {
            response.put("message", "echec de l'enregistrement");
        } else {
            response.put("message", "success");
        }
        return response;
    }

    @PutMapping("")
    private Map<String, Object> update(@RequestBody Contact contact) {
        ContactDTO contactDTO = contactService.update(contact);
        Map<String, Object> response = new HashMap<>();
        if (contactDTO == null) {
            response.put("message", "echec de la mise a jour");
        } else {
            response.put("message", "success");
        }
        return response;
    }

    @PutMapping("del")
    private Map<String, Object> delete(@RequestBody Long id) {
        ContactDTO contactDTO = contactService.delete(id);
        Map<String, Object> response = new HashMap<>();
        if (contactDTO == null) {
            response.put("message", "echec de la suppression");
        } else {
            response.put("message", "success");
        }
        return response;
    }
}
