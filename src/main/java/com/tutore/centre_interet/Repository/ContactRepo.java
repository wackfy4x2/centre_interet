package com.tutore.centre_interet.Repository;

import com.tutore.centre_interet.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact, Long> {
    public Contact findContactById(Long id);
    public Contact findContactByNom(String nom);
}
