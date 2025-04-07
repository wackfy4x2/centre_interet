package com.tutore.centre_interet.Services;

import com.tutore.centre_interet.DTO.ContactDTO;
import com.tutore.centre_interet.Entities.Contact;

public interface ContactService {
    public ContactDTO save(Contact contact);
    public ContactDTO update(Contact contact);
    public ContactDTO delete(Long id);
}
