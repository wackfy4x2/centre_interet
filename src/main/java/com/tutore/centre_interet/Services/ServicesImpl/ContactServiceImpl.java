package com.tutore.centre_interet.Services.ServicesImpl;

import com.tutore.centre_interet.DTO.ContactDTO;
import com.tutore.centre_interet.Entities.Contact;
import com.tutore.centre_interet.Repository.ContactRepo;
import com.tutore.centre_interet.Services.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepo contactRepo;

    public ContactServiceImpl(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    public static ContactDTO toDTO(Contact contact) {
        if (contact == null) {
            return null;
        }
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setNom(contact.getNom());
        dto.setTelephone(contact.getTelephone());
        dto.setEmail(contact.getEmail());
        return dto;
    }

    @Override
    public ContactDTO save(Contact contact) {
        contact.setStatus(true);
        return toDTO(contactRepo.save(contact));
    }

    @Override
    public ContactDTO update(Contact contact) {
        return save(contact);
    }

    @Override
    public ContactDTO delete(Long id) {
        Contact contact = contactRepo.findContactById(id);
        contact.setStatus(false);
        return toDTO(contactRepo.save(contact));
    }
}
