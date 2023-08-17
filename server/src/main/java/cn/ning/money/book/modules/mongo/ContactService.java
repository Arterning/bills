package cn.ning.money.book.modules.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(String id) {
        return contactRepository.findById(id);
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(String id, Contact updatedContact) {
        updatedContact.setId(id); // Ensure the ID is set correctly
        return contactRepository.save(updatedContact);
    }

    public void deleteContact(String id) {
        contactRepository.deleteById(id);
    }
}
